package com.emssanar.cuentas.medicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emssanar.cuentas.medicas.entity.TipoServicio;
import com.emssanar.cuentas.medicas.repository.TipoRepository;

@Service
public class TipoServicioServiceImpl implements TipoServicioService{
	
	 @Autowired
	 private TipoRepository tipoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoServicio> findAll() {
		return tipoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoServicio> findById(Long id) {
		return tipoRepository.findById(id);
	}

}
