package com.emssanar.cuentas.medicas.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emssanar.cuentas.medicas.modelo.Mes;
import com.emssanar.cuentas.medicas.modelo.MesEnum;

@RestController
@RequestMapping(value = "/meses")
public class MesController {

	@GetMapping
	public ResponseEntity<?> getAllTaskStatusValues() {
		return ResponseEntity.ok().body(Arrays.stream(MesEnum.values()).map(me -> {
			Mes m = new Mes();
			m.setDescripcion(me.name());
			return m;
		}).collect(Collectors.toList()));
	}
}
