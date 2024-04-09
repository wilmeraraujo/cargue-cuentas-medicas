package com.emssanar.cuentas.medicas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emssanar.cuentas.medicas.entity.ErrorCargue;

@Repository
public interface ErrorCargueRepository extends MongoRepository<ErrorCargue, String>{

}
