package com.emssanar.cuentas.medicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.emssanar.cuentas.medicas.entity","com.emssanar.cuentas.medicas.modelo"})
@SpringBootApplication
public class CuentasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentasMedicasApplication.class, args);
	}
}
