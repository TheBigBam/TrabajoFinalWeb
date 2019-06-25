package com.upc.model.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    @Query(value = "SELECT r FROM ordenes r WHERE r.fecha = :fecha", nativeQuery = true)
    List<Orden> getOrdenesDia(@Param("fecha") Date fecha);

    @Query(value = "SELECT COUNT(*) FROM facturas WHERE orden_id = :id", nativeQuery = true)
    Integer isActive(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM ordenes as o WHERE NOT EXISTS (SELECT * FROM facturas as f WHERE f.orden_id = o.id)", nativeQuery = true)
    List<Orden> getActiveOrders();
}
