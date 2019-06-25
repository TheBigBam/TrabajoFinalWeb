package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Mesero;
import com.upc.model.repository.MeseroRepository;
import com.upc.service.MeseroService;

@Service
public class MeseroServiceImpl implements MeseroService{
	
	@Autowired
	MeseroRepository meseroRepository;

	@Override
	public Mesero registrar(Mesero t) {
		return meseroRepository.save(t);
	}

	@Override
	public Mesero modificar(Mesero t) {
		return meseroRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		meseroRepository.deleteById(id);
	}

	@Override
	public Optional<Mesero> listId(int id) {
		return meseroRepository.findById(id);
	}

	@Override
	public List<Mesero> listar() {
		return meseroRepository.findAll();
	}
}