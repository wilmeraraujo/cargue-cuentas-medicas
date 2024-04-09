package com.emssanar.cuentas.medicas.service;

import java.util.List;
import java.util.Optional;

import com.emssanar.cuentas.medicas.entity.TipoServicio;

public interface TipoServicioService {
	
	public List<TipoServicio> findAll();
	
	public Optional<TipoServicio> findById(Long id);

}
