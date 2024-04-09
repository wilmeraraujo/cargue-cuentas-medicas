package com.emssanar.cuentas.medicas.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servicios",schema = "pre")
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Consulta> consultas;
	
	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Procedimiento> procedimientos;

	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Medicamento> medicamentos;
	
	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Urgencia> urgencias;
	
	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Hospitalizacion> hospitalizaciones;
	
	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RecienNacido> recienNacidos;
	
	@JsonIgnoreProperties(value = { "servicio" }, allowSetters = true)
	@OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OtroServicio> otrosServicios;
	
	public Servicio() {
		this.consultas = new ArrayList<>();
		this.procedimientos = new ArrayList<>();
		this.medicamentos = new ArrayList<>();
		this.urgencias = new ArrayList<>();
		this.hospitalizaciones = new ArrayList<>();
		this.recienNacidos = new ArrayList<>();
		this.otrosServicios = new ArrayList<>();
		
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas.clear();
		consultas.forEach(this::addConsulta);
	}

	public void addConsulta(Consulta consulta) {
		consulta.setServicio(this);
		this.consultas.add(consulta);
	}
	
	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos.clear();
		procedimientos.forEach(this::addProcedimiento);
	}

	public void addProcedimiento(Procedimiento procedimiento) {
		procedimiento.setServicio(this);
		this.procedimientos.add(procedimiento);
	}
	
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos.clear();
		medicamentos.forEach(this::addMedicamento);
	}

	public void addMedicamento(Medicamento medicamento) {
		medicamento.setServicio(this);
		this.medicamentos.add(medicamento);
	}

	public void setUrgencias(List<Urgencia> urgencias) {
		this.urgencias.clear();
		urgencias.forEach(this::addUrgencia);
	}
	
	public void addUrgencia(Urgencia urgencia) {
		urgencia.setServicio(this);
		this.urgencias.add(urgencia);
	}
	
	public void setHospitalizaciones(List<Hospitalizacion> hospitalizaciones) {
		this.hospitalizaciones.clear();
		hospitalizaciones.forEach(this::addHospitalizacion);
	}
	
	public void addHospitalizacion(Hospitalizacion hospitalizacion) {
		hospitalizacion.setServicio(this);
		this.hospitalizaciones.add(hospitalizacion);
	}
	
	public void setRecienNacidos(List<RecienNacido> recienNacidos) {
		this.recienNacidos.clear();
		recienNacidos.forEach(this::addRecienNacido);
	}
	
	public void addRecienNacido(RecienNacido recienNacido) {
		recienNacido.setServicio(this);
		this.recienNacidos.add(recienNacido);
	}
	
	public void setOtrosServicios(List<OtroServicio> otrosServicios) {
		this.otrosServicios.clear();
		otrosServicios.forEach(this::addOtroServicio);
	}
	
	public void addOtroServicio(OtroServicio otroServicio) {
		otroServicio.setServicio(this);
		this.otrosServicios.add(otroServicio);
	}
}
