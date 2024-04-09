package com.emssanar.cuentas.medicas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emssanar.cuentas.medicas.entity.Anio;
import com.emssanar.cuentas.medicas.service.AnioService;

@RestController
@RequestMapping(value = "/anios")
public class AnioController {

	@Autowired
	private AnioService anioService;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok().body(anioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable(name = "id") Long id) {
		Optional<Anio> anio = anioService.findById(id);

		if (anio.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(anio.get());
	}

}
