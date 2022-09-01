package com.macastil.clase.repository;

import org.springframework.data.repository.CrudRepository;

import com.macastil.clase.bean.db.Celular;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CelularRepository extends CrudRepository<Celular, Integer> {

}
