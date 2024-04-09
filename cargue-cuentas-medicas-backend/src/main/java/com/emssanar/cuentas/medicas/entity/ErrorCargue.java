package com.emssanar.cuentas.medicas.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document(collection = "errores_cargue")
public class ErrorCargue {
	
	@Id
	private String id;
	private String campo;
	private String error;
	private String valorAsociado;
	private Cargue cargue;
	private Long numeroLinea;

}
