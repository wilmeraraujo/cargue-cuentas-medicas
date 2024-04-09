package com.emssanar.cuentas.medicas.service;

import java.util.List;
import java.util.Optional;

import com.emssanar.cuentas.medicas.entity.Anio;

public interface AnioService {

	public List<Anio> findAll();

	public Optional<Anio> findById(Long id);

}
