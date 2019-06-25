package com.upc.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Orden;
import com.upc.model.repository.DetalleOrdenRepository;
import com.upc.model.repository.OrdenRepository;
import com.upc.service.OrdenService;

@Service
public class OrdenServiceImpl implements OrdenService{
	

	@Autowired
	OrdenRepository ordenRepository;
	
	@Autowired
	DetalleOrdenRepository detalleOrdenRepository;

	@Override
	public Orden registrar(Orden t) {
		System.out.println(t.getNro_clientes());
		return ordenRepository.save(t);
	}

	@Override
	public Orden modificar(Orden t) {
		ordenRepository.ParcheMesa(t.getMesa().getId());
		return ordenRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		ordenRepository.deleteById(id);
	}

	@Override
	public Optional<Orden> listId(int id) {
		return ordenRepository.findById(id);
	}
	
	@Override
	public List<Orden> getOrdenesDia(Date fecha){
		return ordenRepository.getOrdenesDia(fecha);
	}

	@Override
	public List<Orden> listar() {
		return ordenRepository.findAll();
	}
	
	@Transactional
	@Override
	public Orden registrarPedidos(Orden orden) {
		orden.getDetalle_orden().forEach(detalle->
		detalle.setOrden(orden));
		
		ordenRepository.save(orden);
				
		return orden;
	}
}