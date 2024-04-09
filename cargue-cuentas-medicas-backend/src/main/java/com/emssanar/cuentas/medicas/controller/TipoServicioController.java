package com.emssanar.cuentas.medicas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emssanar.cuentas.medicas.entity.TipoServicio;
import com.emssanar.cuentas.medicas.service.TipoServicioService;

@RestController
@RequestMapping(value = "/servicios")
public class TipoServicioController {
	
	@Autowired
	private TipoServicioService tipoServicioService;

	
	@GetMapping
	public ResponseEntity<?> buscarTodos(){		
		return ResponseEntity.ok().body(tipoServicioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable(name = "id")Long id){
		Optional<TipoServicio> tipoServicio = tipoServicioService.findById(id);
		
		if(tipoServicio.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(tipoServicio.get());
	}
}
