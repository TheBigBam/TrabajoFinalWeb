package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
	
	@Query(value="select m from mesas m where m.estado='Disponible'",nativeQuery=true)
	List<Mesa> listaMesasDisponibles();

}
