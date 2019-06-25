package com.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Mesero;

@Repository
public interface MeseroRepository extends JpaRepository<Mesero, Integer> {

}
