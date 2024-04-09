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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facturas",schema = "pre")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 1, max=20)
	private String numDocumentoldObligado;
	private String numFactura;
	private String tipoNota;
	@Size(min = 1, max=20)
	private String numNota;

	@JsonIgnoreProperties(value = { "factura" }, allowSetters = true)
	@OneToMany(mappedBy = "factura", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Usuario> usuarios;

	public Factura() {
		this.usuarios = new ArrayList<>();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios.clear();
		usuarios.forEach(this::addUsuario);
	}

	public void addUsuario(Usuario usuario) {
		usuario.setFactura(this);
		this.usuarios.add(usuario);
	}

}
