package com.upc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upc.model.entity.Mesa;

@Service
public interface MesaService extends CrudService<Mesa> {
	
	List<Mesa> listaMesasDisponibles();

}
