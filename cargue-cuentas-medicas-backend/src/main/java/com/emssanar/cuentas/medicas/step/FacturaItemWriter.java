package com.emssanar.cuentas.medicas.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emssanar.cuentas.medicas.entity.Factura;
import com.emssanar.cuentas.medicas.service.FacturaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FacturaItemWriter implements ItemWriter<Factura> {

	@Autowired
	private FacturaService facturaService;

	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends Factura> facturas) throws Exception {
		log.info("Ingreso al writer");
		facturaService.saveAll((List<Factura>) facturas);
	}
}
