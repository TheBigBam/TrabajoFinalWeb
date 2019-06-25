package com.upc.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upc.model.entity.Orden;

@Service
public interface OrdenService extends CrudService<Orden> {
	List<Orden> getOrdenesDia(Date fecha);
	Integer isActive(Integer id);
	List<Orden> getActiveOrders();
}