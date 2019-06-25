package com.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer> {

}
