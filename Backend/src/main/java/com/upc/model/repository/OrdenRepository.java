package com.upc.model.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    @Query(value = "SELECT r FROM ordenes r WHERE r.fecha = :fecha", nativeQuery = true)
    List<Orden> getOrdenesDia(@Param("fecha") Date fecha);
    
    @Modifying
   	@Query(value="UPDATE m SET m.estado='Ocupada' FROM mesas m WHERE m.id=:mesa_id",nativeQuery=true)
   	void ParcheMesa(@Param("mesa_id") Integer mesa_id);
}
