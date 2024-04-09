package com.emssanar.cuentas.medicas.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emssanar.cuentas.medicas.entity.Cargue;
import com.emssanar.cuentas.medicas.repository.CargueRepository;

@Service
public class CargueServiceImpl implements CargueService{

	@Autowired
	private CargueRepository cargueRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cargue> findAll(Pageable pageable) {
		return cargueRepository.findAll(pageable);
	}

	@Override
	@Transactional	
	public Cargue save(Cargue cargue) {
		return cargueRepository.save(cargue);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cargue> findById(Long id) {
		return cargueRepository.findById(id);
	}

}
