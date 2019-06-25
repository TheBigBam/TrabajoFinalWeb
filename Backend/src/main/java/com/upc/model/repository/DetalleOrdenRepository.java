package com.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.DetalleOrden;


@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

	@Modifying
	@Query(value="INSERT INTO detalle_ordenes(orden_id,comida_id,cantidad) VALUES(:orden_id,:comida_id,:cantidad) ",nativeQuery=true)
	Integer registrardetalle(@Param("orden_id") Integer orden_id,
			@Param("comida_id") Integer comida_id,@Param("cantidad") Integer cantidad);
	
	@Modifying
	@Query(value="DELETE FROM detalle_ordenes WHERE orden_id = :id ",nativeQuery=true)
	Integer eliminarDetallesDeOrdenID(@Param("id") Integer id);
}