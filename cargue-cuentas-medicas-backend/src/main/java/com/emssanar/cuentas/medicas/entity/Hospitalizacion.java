package com.emssanar.cuentas.medicas.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mongodb.lang.Nullable;

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
@Entity
@Table(name = "hospitalizaciones",schema = "pre")

public class Hospitalizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nullable
	@Size(min = 12, max=12, message = "La longitud del campo debe ser de 12 caracteres o debe ser un campo nulo")	
	private String codPrestador;
	
	@Column(name="via_ingreso_servicio_salud")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente de 2 caracteres")	
	private String viaIngresoServicioSalud; 
	
	@Column(name="fecha_inicio_atencion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fechaInicioAtencion; 
	
	@Nullable	
	@Size(min = 0, max=30, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String numAutorizacion;
	
	@Column(name="causa_motivo_atencion")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente de 2 caracteres")	
	private String causaMotivoAtencion; 
	
	@Column(name="cod_diagnostico_principal")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres")	
	private String codDiagnosticoPrincipal;	
	
	@Column(name="cod_diagnostico_principal_e")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres")	
	private String codDiagnosticoPrincipalE;
	
	@Nullable
	@Column(name="cod_diagnostico_relacionado_e1")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres o debe ser un campo vacío ")	
	private String codDiagnosticoRelacionadoE1;
	
	@Nullable
	@Column(name="cod_diagnostico_relacionado_e2")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres o debe ser un campo vacío ")	
	private String codDiagnosticoRelacionadoE2;
	
	@Nullable
	@Column(name="cod_diagnostico_relacionado_e3")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres o debe ser un campo vacío")	
	private String codDiagnosticoRelacionadoE3;
	
	@Nullable	
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres o debe ser un campo vacío")	
	private String codComplicacion;
	
	@Column(name="condicion_destino_usuario_egreso")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente de 2 caracteres")	
	private String condicionDestinoUsuarioEgreso; 
	
	@Nullable
	@Column(name="cod_diagnostico_causa_muerte")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres o debe ser un campo vacío")	
	private String codDiagnosticoCausaMuerte;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fechaEgreso; 
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Digits(integer = 7, fraction = 0, message = "El número debe tener entre 1 y 7 dígitos")
	private int consecutivo;	
	
	@JsonIgnoreProperties(value = { "hospitalizaciones" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;

}
