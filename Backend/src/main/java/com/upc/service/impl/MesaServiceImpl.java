package com.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Mesa;
import com.upc.model.repository.MesaRepository;
import com.upc.service.MesaService;

@Service
public class MesaServiceImpl implements MesaService{
	
	@Autowired
	MesaRepository mesaRepository;
	
	@Override
	public List<Mesa> listaMesasDisponibles(){
		return mesaRepository.listaMesasDisponibles();
	}
	
	@Override
	public Mesa registrar(Mesa t) {
		return mesaRepository.save(t);
	}

	@Override
	public Mesa modificar(Mesa t) {
		return mesaRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		mesaRepository.deleteById(id);
	}

	@Override
	public Optional<Mesa> listId(int id) {
		return mesaRepository.findById(id);
	}

	@Override
	public List<Mesa> listar() {
		return mesaRepository.findAll();
	}
}