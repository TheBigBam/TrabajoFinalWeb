package com.upc.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upc.model.entity.Factura;

@Service
public interface FacturaService extends CrudService<Factura> {
	
	Factura registrar(Factura factura);
	
	List<Factura> getFacturasDia(Date fecha);
}