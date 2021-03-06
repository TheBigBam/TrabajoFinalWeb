package com.upc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upc.model.entity.Factura;
import com.upc.model.repository.FacturaRepository;
import com.upc.model.repository.MesaRepository;
import com.upc.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService{
	
	@Autowired
	FacturaRepository facturaRepository;
	
	@Autowired
	MesaRepository mesaRepository;

	@Transactional
	@Override
	public Factura registrar(Factura t) {
		Float monto_total=facturaRepository.calculoMontoFinal(t.getOrden().getId());
		t.setMonto_total(monto_total);
		mesaRepository.setEstadoMesa(t.getOrden().getMesa().getId(), "Disponible");
		return facturaRepository.save(t);
	}

	@Override
	public Factura modificar(Factura t) {
		return facturaRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		facturaRepository.deleteById(id);
	}

	@Override
	public Optional<Factura> listId(int id) {
		return facturaRepository.findById(id);
	}
	
	@Override
	public List<Factura> getFacturasDia(Date fecha){
		return facturaRepository.getFacturasDia(fecha);
	}
	
	@Override
	public List<Factura> listar() {
		return facturaRepository.findAll();
	}
}