package com.emssanar.cuentas.medicas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emssanar.cuentas.medicas.entity.Cargue;

public interface CargueRepository extends JpaRepository<Cargue, Long> {

	public Page<Cargue> findAllByOrderByIdAsc(Pageable pageable);

}
