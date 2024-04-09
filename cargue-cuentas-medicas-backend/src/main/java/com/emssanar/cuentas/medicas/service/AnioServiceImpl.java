package com.emssanar.cuentas.medicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emssanar.cuentas.medicas.entity.Anio;
import com.emssanar.cuentas.medicas.repository.AnioRepository;

@Service
public class AnioServiceImpl implements AnioService {
	
	@Autowired
	private AnioRepository anioRepository;

	@Override
	public List<Anio> findAll() {
		return anioRepository.findAll();
	}

	@Override
	public Optional<Anio> findById(Long id) {
		return anioRepository.findById(id);
	}

}
