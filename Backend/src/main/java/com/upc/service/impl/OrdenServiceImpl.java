package com.upc.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Orden;
import com.upc.model.repository.DetalleOrdenRepository;
import com.upc.model.repository.MesaRepository;
import com.upc.model.repository.OrdenRepository;
import com.upc.service.OrdenService;

@Service
public class OrdenServiceImpl implements OrdenService{
	@Autowired
	OrdenRepository ordenRepository;
	
	@Autowired
	MesaRepository mesaRepository;
	
	@Autowired
	DetalleOrdenRepository detalleOrdenRepository;

	@Transactional
	@Override
	public Orden registrar(Orden orden) {
		orden.getDetalle_orden().forEach(detalle->
		detalle.setOrden(orden));
		
		mesaRepository.setEstadoMesa(orden.getMesa().getId(), "Ocupada");
		ordenRepository.save(orden);
				
		return orden;
	}

	@Transactional
	@Override
	public Orden modificar(Orden t) {
		detalleOrdenRepository.eliminarDetallesDeOrdenID(t.getId());
		
		t.getDetalle_orden().forEach(detalle->
		detalle.setOrden(t));
		
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
	
	@Override
	public Integer isActive(Integer id) {
		Integer temp = ordenRepository.isActive(id);
		if(temp == 0) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public List<Orden> getActiveOrders(){
		return ordenRepository.getActiveOrders();
	}
}