package com.emssanar.cuentas.medicas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "batch_job_execution")
public class EjecucionTarea {
	
	@Id
	@Column(name = "job_execution_id")
	private Long id;
	
	private String status;
	

}
