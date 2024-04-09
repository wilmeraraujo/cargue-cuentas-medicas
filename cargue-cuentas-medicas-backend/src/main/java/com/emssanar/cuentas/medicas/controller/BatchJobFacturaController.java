package com.emssanar.cuentas.medicas.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emssanar.cuentas.medicas.config.Constantes;
import com.emssanar.cuentas.medicas.entity.Cargue;
import com.emssanar.cuentas.medicas.modelo.CargueDto;
import com.emssanar.cuentas.medicas.service.CargueService;
import com.emssanar.cuentas.medicas.service.EjecucionTareaService;

import org.springframework.batch.core.repository.JobRestartException;

@RestController
@RequestMapping(value = "/cargues")
public class BatchJobFacturaController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
	@Value("${ruta.storage}")
	private String rutaStorage;
	
	@Autowired
	private CargueService cargueService;
	
	@Autowired
	private EjecucionTareaService ejecucionTareaService;
	
	@GetMapping("/paginable")
	public ResponseEntity<?> listAll(Pageable pageable){
		return ResponseEntity.ok().body(cargueService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listById(@PathVariable(name = "id") Long id){
		Optional<Cargue> cargue = cargueService.findById(id);
		
		if(cargue.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cargue.get());		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Validated @RequestBody Cargue cargue, BindingResult result, @PathVariable(name = "id") Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Cargue> c = cargueService.findById(id);
		
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cargue cargueDb = c.get();
		cargueDb.setMes(cargue.getMes());
		cargueDb.setAnio(cargue.getAnio());
		cargueDb.setTipoServicio(cargue.getTipoServicio());
				
		return  ResponseEntity.status(HttpStatus.CREATED).body(cargueService.save(cargueDb));
	}

	/*
	 * Metodo que permite procesar un cargue, donde recibe como adjunto un archivo json
	 * el cual debe contener la esrtuctura de la resolución 1036, para poder ser procesado.
	 * Ademas crea una entidad cargue que permite asociar el cargue, a la tarea que se ejecuta
	 * de manera asincronica. 
	 */
	@PostMapping(path = "/procesar-cargue")
	public ResponseEntity<?> startFacturaBatch(@Validated @ModelAttribute Cargue cargue, BindingResult result, @RequestParam("file") MultipartFile multipartFile) {

		
		try {
			String originalFileName = multipartFile.getOriginalFilename();
			File fileToImport = new File(rutaStorage + originalFileName);
			multipartFile.transferTo(fileToImport);

			if(result.hasErrors()) {
				return this.validar(result);
			}
			
			if(!multipartFile.isEmpty()) {
				
				cargue.setNombreArchivo(originalFileName);				
				Cargue entityDb = cargueService.save(cargue);
				
				JobParameters jobParameters = new JobParametersBuilder()
						.addString("fullPathFileName", rutaStorage + originalFileName)
						.addLong("identificadorCargue", entityDb.getId())
						.addLong("startAt", System.currentTimeMillis()).toJobParameters();
				
				JobExecution execution = jobLauncher.run(job, jobParameters);
				
				Optional <Cargue> cargueActualizar = cargueService.findById(entityDb.getId());
				cargueActualizar.get().setEjecucionTarea(ejecucionTareaService.finById(execution.getId()).get());
				
				cargueService.save(cargueActualizar.get());
				
				CargueDto cargueDto = CargueDto
						.builder()
						.mes(cargueActualizar.get().getAnio())
						.anio(cargueActualizar.get().getAnio())
						.tipoServicio(cargueActualizar.get().getTipoServicio())
						.nombreArchivo(cargueActualizar.get().getNombreArchivo())
						.build();
				
				if (execution.getExitStatus().getExitCode().equals(Constantes.STATUS_COMPLETED)) {
					//Files.deleteIfExists(Paths.get(rutaStorage + originalFileName));
				}
				
				return ResponseEntity.status(HttpStatus.CREATED).body(cargueDto);				
				
			}else {
				return ResponseEntity.badRequest().body("Error no adjunto el archivo");
			}
			

		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException | IOException  e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Error");
		}
	}
	
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(e -> {
			errores.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}	
}
