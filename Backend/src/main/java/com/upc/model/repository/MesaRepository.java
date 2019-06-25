package com.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
	@Query(value="SELECT * FROM mesas WHERE estado = 'Disponible'",nativeQuery=true)
	List<Mesa> listaMesasDisponibles();
	
    @Modifying
   	@Query(value="UPDATE mesas SET estado=:estado WHERE id=:mesa_id",nativeQuery=true)
   	void setEstadoMesa(@Param("mesa_id") Integer mesa_id, @Param("estado") String estado);
}
