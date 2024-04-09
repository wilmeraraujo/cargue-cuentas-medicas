package com.emssanar.cuentas.medicas.service;

import java.util.List;

import com.emssanar.cuentas.medicas.entity.Factura;

public interface FacturaService {
	 Iterable<Factura> saveAll(List<Factura> facturaList);
	 Factura save(Factura factura);
}
