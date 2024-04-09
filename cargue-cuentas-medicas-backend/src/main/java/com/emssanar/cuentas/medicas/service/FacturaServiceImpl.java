package com.emssanar.cuentas.medicas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emssanar.cuentas.medicas.entity.Factura;
import com.emssanar.cuentas.medicas.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService{

	@Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Iterable<Factura> saveAll(List<Factura> facturaList) {
        return facturaRepository.saveAll(facturaList);
    }

	@Override
	public Factura save(Factura factura) {
		return facturaRepository.save(factura);
	}

}
