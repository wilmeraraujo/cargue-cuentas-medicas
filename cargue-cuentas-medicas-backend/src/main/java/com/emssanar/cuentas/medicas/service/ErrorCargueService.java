package com.emssanar.cuentas.medicas.service;

import com.emssanar.cuentas.medicas.entity.ErrorCargue;

public interface ErrorCargueService {
	
	public Iterable<ErrorCargue> saveAll(Iterable<ErrorCargue> errores);

}
