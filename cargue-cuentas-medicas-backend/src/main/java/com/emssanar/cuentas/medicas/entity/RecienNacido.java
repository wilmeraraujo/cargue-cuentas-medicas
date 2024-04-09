package com.emssanar.cuentas.medicas.entity;

import java.time.LocalDate;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
@Table(name = "recienNacidos",schema = "pre")

public class RecienNacido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Size(min = 12, max=12, message = "La longitud del campo debe ser de 12 caracteres o debe ser un campo nulo")	
	private String codPrestador;
	
	@Column(name = "tipo_documento_identificacion")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String tipoDocumentoIdentificacion;	
	
	@Column(name = "num_documento_identificacion")
	@Size(min = 4, max=20, message = "La longitud del campo no corresponde a un dato válido")
	private String numDocumentoIdentificacion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fechaNacimiento;
	
	@Digits(integer = 2, fraction = 0, message = "El número debe tener 2 dígitos.")
	@Min(value = 10, message = "El número debe tener 2 dígitos.")
	@Max(value = 99, message = "El valor debe tener 2 dígitos")
	private int edadGestacional;
	
	@Column(name = "num_consultas_c_prenatal")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String numConsultasCPrenatal;	
	
	
	@Column(name = "cod_sexo_biologico")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
	private String codSexoBiologico;

	//Esta variable debe estar en un rango entre 3 a 4 dígitos. Se omite la anotación Digits.
	@Min(value = 100, message = "El número debe tener mínimo 3 dígitos.")
	@Max(value = 9999, message = "El valor debe ser de máximo 4 dígitos")
	private int peso;
	
	@Column(name="cod_diagnostico_principal")
	@Size(min = 4, max=25, message = "La longitud debe ser entre 4 y 25 caracteres")	
	private String codDiagnosticoPrincipal;	
	
	@Column(name = "condicion_destino_usuario_egreso")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente 2 caracteres")
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
	
	@JsonIgnoreProperties(value = { "recienNacidos" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;

}
