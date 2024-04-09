package com.emssanar.cuentas.medicas.service;

import java.util.Optional;

import com.emssanar.cuentas.medicas.modelo.EjecucionTarea;

public interface EjecucionTareaService {

	Optional<EjecucionTarea> finById(Long id);

}
