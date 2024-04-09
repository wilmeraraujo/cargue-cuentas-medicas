package com.emssanar.cuentas.medicas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emssanar.cuentas.medicas.modelo.EjecucionTarea;
import com.emssanar.cuentas.medicas.repository.EjecucionTareaRepository;

@Service
public class EjecucionTareaServiceImpl implements EjecucionTareaService{
	
	@Autowired
	private EjecucionTareaRepository ejecucionTareaRepository;

	@Override
	public Optional<EjecucionTarea> finById(Long id) {
		return ejecucionTareaRepository.findById(id);
	}

}
