package com.upc.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upc.model.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>{
	
	@Query(value = "SELECT * FROM menus r WHERE r.fecha = :fecha", nativeQuery = true)
    List<Menu> getMenudelDia(@Param("fecha") Date fecha);
}
