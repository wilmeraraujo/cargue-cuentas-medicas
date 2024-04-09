package com.emssanar.cuentas.medicas.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CargueDto {

	private String mes;
	private String anio;
	private String tipoServicio;
	private String nombreArchivo;
}
