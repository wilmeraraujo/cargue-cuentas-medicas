package com.emssanar.cuentas.medicas.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.emssanar.cuentas.medicas.modelo.EjecucionTarea;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cargues", schema = "pre")
public class Cargue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String mes;

	@NotNull
	private String anio;

	@NotNull
	private String tipoServicio;
	
	private String nombreArchivo;

	@JsonIgnoreProperties
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job_execution_id")
	private EjecucionTarea ejecucionTarea;
}
