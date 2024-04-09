
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Table(name = "otrosServicios",schema = "pre")

public class OtroServicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nullable
	@Size(min = 12, max=12, message = "La longitud del campo debe ser de 12 caracteres o debe ser un campo nulo")	
	private String codPrestador;

	@Nullable
	@Size(min = 0, max=30, message = "La longitud del campo no es válida o debe ser un campo vacío")	
	private String numAutorizacion;
	
	@Nullable
	@Size(min = 0, max=15, message = "La longitud del campo no es válida o debe ser un campo vacío")	
	private String idMIPRES;
	
	@Column(name="fecha_suministro_tecnologia")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fechaSuministroTecnologia;
		
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente de 2 caracteres")	
	private String tipoOS;
		
	@Column(name="cod_tecnologia_salud")
	@Size(min = 0, max=20, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String codTecnologiaSalud;
	
	@Nullable
	@Column(name="nom_tecnologia_salud")
	@Size(min = 0, max=60, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String nomTecnologiaSalud;
	
	@Column(name="cantidad_o_s")
	@Digits(integer = 5, fraction = 0, message = "El número debe tener 5 dígitos.")
	@Min(value = 10000, message = "El número debe tener 5 dígitos.")
	@Max(value = 99999, message = "El valor debe tener 5 dígitos")
	private int cantidadOS;
	
	@Column(name="tipo_documento_identificacion")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente de 2 caracteres")	
	private String tipoDocumentoIdentificacion;
	
	@Column(name="num_documento_identificacion")
	@Size(min = 4, max=20, message = "La longitud debe ser entre 4 y 20 caracteres")	
	private String numDocumentoIdentificacion;
	
	@Column(name="vr_unit_o_s")
	@Digits(integer = 15, fraction = 0, message = "El número debe tener hasta 15 dígitos.") //Consultar
	@Min(value = 1, message = "El número debe tener al menos 1 dígito.")
	@Max(value = 999999999999999L, message = "El número no puede tener más de 15 dígitos.")
	private long vrUnitOS;	
	
	@Digits(integer = 15, fraction = 0, message = "El número debe tener hasta 15 dígitos.") //Consultar
	@Min(value = 1, message = "El número debe tener al menos 1 dígito.")
	@Max(value = 999999999999999L, message = "El número no puede tener más de 15 dígitos.")
	private long vrServicio;
	
	@Column(name="tipo_pago_moderador")
	@Size(min = 2, max=2, message = "La longitud debe ser exactamente de 2 caracteres")	
	private String tipoPagoModerador;
	
	@Column(name="valor_pago_moderador")
	@Digits(integer = 10, fraction = 0, message = "El número debe tener hasta 10 dígitos.")
	@Min(value = 1, message = "El número debe tener al menos 1 dígito.")
	@Max(value = 9999999999L, message = "El número no puede tener más de 10 dígitos.")
	private long valorPagoModerador;
	
	@Nullable
	@Column(name="num_fev_pago_moderador")
	@Size(min = 1, max=20, message = "La longitud del campo no es válida o debe ser un campo vacío")
	private String numFEVPagoModerador;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Digits(integer = 7, fraction = 0, message = "El número debe tener entre 1 y 7 dígitos")
	private int consecutivo;
	
	@JsonIgnoreProperties(value = { "otrosServicios" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;

}
