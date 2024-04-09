package com.emssanar.cuentas.medicas.step;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.emssanar.cuentas.medicas.entity.ErrorCargue;
import com.emssanar.cuentas.medicas.entity.Factura;
import com.emssanar.cuentas.medicas.service.CargueService;
import com.emssanar.cuentas.medicas.service.ErrorCargueService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacturaItemProcessor implements ItemProcessor<Factura, Factura>{	

	private final Validator validator;
	
	@Autowired
	private ErrorCargueService errorCargueService;
	
	@Autowired     
	private CargueService cargueService; 
	
	private Long cargueId;
	        

    public FacturaItemProcessor(Validator validator, Long cargueId) {
        this.validator = validator;
        this.cargueId = cargueId;
    }
    
	@Override
	public Factura process(Factura facturaItem) throws Exception {
		  if(facturaItem == null){
	            log.error("El item de ItemProcessor es null");
	            throw new RuntimeException();
	        }
		  
		  Set<ConstraintViolation<Factura>> violations = validator.validate(facturaItem);  
		  
	        if (!violations.isEmpty()) {
	        	List<ErrorCargue> listaErrores = new ArrayList<>();	        	
	        	
	        	violations.forEach(violation -> {
	        		ErrorCargue e = ErrorCargue
	        		.builder()
	        		.campo(violation.getPropertyPath().toString())
	        		.error(violation.getMessage())
	        		.valorAsociado(violation.getInvalidValue().toString())
	        		.cargue(cargueService.findById(cargueId).get())
	        		.build();	        		
	        		listaErrores.add(e);
	        	});
	        	errorCargueService.saveAll(listaErrores);
	        	
	            throw new ValidationException("Error de validación: " + violations.toString());
	        }
	       	       	        
		  Factura factura = new Factura();
		  factura.setNumDocumentoldObligado(facturaItem.getNumDocumentoldObligado());
		  factura.setNumFactura(facturaItem.getNumFactura());
		  factura.setTipoNota(facturaItem.getTipoNota());
		  factura.setNumNota(facturaItem.getNumNota());
		  factura.setUsuarios(facturaItem.getUsuarios());
		  return factura;
	}	
}
