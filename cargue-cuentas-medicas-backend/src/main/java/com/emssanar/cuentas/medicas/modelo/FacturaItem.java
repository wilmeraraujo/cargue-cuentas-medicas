package com.emssanar.cuentas.medicas.modelo;

import java.util.List;

import javax.validation.constraints.Size;

import com.emssanar.cuentas.medicas.entity.Procedimiento;
import com.emssanar.cuentas.medicas.entity.Servicio;
import com.emssanar.cuentas.medicas.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FacturaItem {
	@Size(min = 1, max=2)
	private String numDocumentoldObligado;
	private String numFactura;
	private String tipoNota;
	private String numNota;
	private List<Usuario> usuarios;
	private List<Servicio> servicios;
	private List<Procedimiento> procedimientos;

}
