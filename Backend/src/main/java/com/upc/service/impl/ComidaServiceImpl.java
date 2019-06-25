package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Comida;
import com.upc.model.repository.ComidaRepository;
import com.upc.service.ComidaService;

@Service
public class ComidaServiceImpl implements ComidaService{
	
	@Autowired
	ComidaRepository comidaRepo;

	@Override
	public Comida registrar(Comida t) {
		return comidaRepo.save(t);
	}

	@Override
	public Comida modificar(Comida t) {
		return comidaRepo.save(t);
	}

	@Override
	public void eliminar(int id) {
		comidaRepo.deleteById(id);
	}

	@Override
	public Optional<Comida> listId(int id) {
		return comidaRepo.findById(id);
	}

	@Override
	public List<Comida> listar() {
		return comidaRepo.findAll();
	}
}