package com.emssanar.cuentas.medicas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emssanar.cuentas.medicas.entity.ErrorCargue;
import com.emssanar.cuentas.medicas.repository.ErrorCargueRepository;

@Service
public class ErrorCargueServiceImpl implements ErrorCargueService{
	
	@Autowired
	private ErrorCargueRepository errorCargueRepository;

	@Override
	@Transactional
	public Iterable<ErrorCargue> saveAll(Iterable<ErrorCargue> errores) {
		return errorCargueRepository.saveAll(errores);
	}

}
