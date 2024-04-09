package com.emssanar.cuentas.medicas.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emssanar.cuentas.medicas.entity.Cargue;

public interface CargueService {
	
	public Page<Cargue> findAll(Pageable pageable);
	
	public Cargue save(Cargue cargue);
	
	public Optional<Cargue> findById(Long id);

}
