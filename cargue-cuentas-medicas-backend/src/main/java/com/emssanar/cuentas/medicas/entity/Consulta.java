package com.emssanar.cuentas.medicas.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "consultas",schema = "pre")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Size(min = 12, max=12, message = "La longitud debe ser exactamente 12 caracteres")
	private String codPrestador;
	
	@Column(name="fecha_inicio_atencion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fechaInicioAtencion;

	
	@Size(min = 0, max=30, message = "La longitud del campo no es válida")
	private String numAutorizacion;
	
	@Size(min = 6, max=6, message = "La longitud debe ser exactamente 6 caracteres")
	private String codConsulta;
	
	@Column(name="modalidad_grupo_servicio_tec_sal")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String modalidadGrupoServicioTecSal;
	
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String grupoServicios;

	@Min(value = 100, message = "El valor debe ser de al menos 3 dígitos")
	@Max(value = 9999, message = "El valor debe ser de máximo 4 dígitos")
	private int codServicio;
	
	@Column(name="finalidad_tecnologia_salud")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String finalidadTecnologiaSalud;
	
	@Column(name="causa_motivo_atencion")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String causaMotivoAtencion;

	@Column(name="cod_diagnostico_principal")
	@Size(min = 4, max=25, message = "La longitud del campo no corresponde a un dato válido")
	private String codDiagnosticoPrincipal;
	
	@Nullable
	@Column(name="cod_diagnostico_relacionado1")
	@Size(min = 4, max=25, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String codDiagnosticoRelacionado1;
	
	@Nullable
	@Column(name="cod_diagnostico_relacionado2")
	@Size(min = 4, max=25, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String codDiagnosticoRelacionado2;
	
	@Nullable
	@Column(name="cod_diagnostico_relacionado3")
	@Size(min = 4, max=25, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String codDiagnosticoRelacionado3;
	
	@Column(name="tipo_diagnostico_principal")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String tipoDiagnosticoPrincipal;
	
	@Column(name="tipo_documento_identificacion")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String tipoDocumentoIdentificacion;
	
	@Column(name="num_documento_identificacion")
	@Size(min = 4, max=20, message = "La longitud del campo no es válida")	
	private String numDocumentoIdentificacion;
	
	@Min(value = 0, message = "El valor debe ser de al menos 1 dígito")
    @Max(value = 9999999999L, message = "El valor debe ser de máximo 10 dígitos")	
	private long vrServicio;
	
	@Column(name="tipo_pago_moderador")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String tipoPagoModerador;
	
	@Column(name="valor_pago_moderador")
	@Min(value = 0, message = "El valor debe ser de al menos 1 dígito")
    @Max(value = 9999999999L, message = "El valor debe ser de máximo 10 dígitos")	
	private long valorPagoModerador;
	
	@Nullable
	@Column(name="num_fev_pago_moderador")
	@Size(min = 1, max=20, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String numFEVPagoModerador;
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Digits(integer = 7, fraction = 0, message = "El número debe tener entre 1 y 7 dígitos")
	@Max(value = 9999999, message = "El valor debe ser de máximo 7 dígitos")
	private int consecutivo;	

    	
	@JsonIgnoreProperties(value = { "consultas" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;
	
	

}
