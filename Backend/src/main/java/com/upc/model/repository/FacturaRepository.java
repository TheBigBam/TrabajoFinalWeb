package com.upc.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    @Query(value = "SELECT f FROM facturas f WHERE f.reserva = f.fecha_generacion = :fecha", nativeQuery = true)
    List<Factura> getFacturasDia(@Param("fecha") Date fecha);
    
    @Query(value = "SELECT SUM(d.cantidad*c.precio) FROM detalle_ordenes d , comidas c WHERE d.comida_id=c.id AND d.orden_id=:orden_id", nativeQuery = true)
    Float CalculoMontoFinal(@Param("orden_id") Integer orden_id);
    
    @Modifying
	@Query(value="UPDATE m SET m.estado='Disponible' FROM mesas m,orden o WHERE m.id=o.mesa_id AND o.id=:orden_id ",nativeQuery=true)
	void ParcheMesa(@Param("orden_id") Integer orden_id);
}
