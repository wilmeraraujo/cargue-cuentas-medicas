package com.emssanar.cuentas.medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emssanar.cuentas.medicas.entity.TipoServicio;

public interface TipoRepository extends JpaRepository<TipoServicio, Long>{

}
