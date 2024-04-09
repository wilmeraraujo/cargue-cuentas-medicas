package com.emssanar.cuentas.medicas.entity;


import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "usuarios",schema = "pre")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_documento_identificacion")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String tipoDocumentoIdentificacion;	
	
	@Column(name = "num_documento_identificacion")
	@Size(min = 4, max=20, message = "La longitud del campo no corresponde a un dato válido")
	private String numDocumentoIdentificacion;
	
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String tipoUsuario;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fechaNacimiento;

	
	@Size(min = 1, max=1, message = "La longitud debe ser exactamente 1 caracter")
	private String codSexo;
	
	@Column(name = "cod_pais_residencia")
	@Size(min = 3, max=3, message = "La longitud debe ser exactamente 3 caracteres")
	private String codPaisResidencia;
	
	@Nullable
	@Column(name = "cod_municipio_residencia")
	@Size(min = 5, max=5, message = "La longitud debe ser exactamente 5 caracteres o no debe contener dato")
	private String codMunicipioResidencia;
	
	@Nullable
	@Column(name = "cod_zona_territorial_residencia")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres o no debe contener dato")
	private String codZonaTerritorialResidencia;
	
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String incapacidad;
	
	@Column(name = "cod_pais_origen")
	@Size(min = 3, max=3, message = "La longitud debe ser exactamente 3 caracteres")
	private String codPaisOrigen;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Digits(integer = 7, fraction = 0, message = "El número debe tener entre 1 y 7 dígitos")
	private int consecutivo;	
	

	@JsonIgnoreProperties(value = { "usuarios" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "factura_id")
	private Factura factura;

	@JsonIgnoreProperties(value = " {usuarios} ")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "servicio_id")
	private Servicio servicios;

	public Usuario() {
		servicios = new Servicio();
	}
}
