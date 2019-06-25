package com.upc.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    @Query(value = "SELECT f FROM facturas f WHERE f.reserva = f.fecha_generacion = :fecha", nativeQuery = true)
    List<Factura> getFacturasDia(@Param("fecha") Date fecha);
    
    @Query(value = "SELECT SUM(d.cantidad*c.precio) FROM detalle_ordenes AS d, comidas AS c WHERE d.orden_id = :id AND d.comida_id=c.id", nativeQuery = true)
    Float calculoMontoFinal(@Param("id") Integer id);
}
