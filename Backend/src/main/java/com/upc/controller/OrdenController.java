package com.upc.controller;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upc.exception.ModeloNotFoundException;
import com.upc.model.entity.Orden;
import com.upc.service.OrdenService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {
	@Autowired
	private OrdenService ordenService;
	
	@ApiOperation("Retorna la lista completa de ordenes")
	@GetMapping
	public ResponseEntity<List<Orden>> listar(){
		List<Orden> ordens = new ArrayList<>();
		ordens = ordenService.listar();
		return new ResponseEntity<List<Orden>>(ordens, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna la lista de ordenes de una fecha determinada")
	@GetMapping(value = "/fecha/{date}")
	public ResponseEntity<List<Orden>> listarFecha(@PathVariable("date") Date date){
		List<Orden> ordens = new ArrayList<>();
		ordens = ordenService.getOrdenesDia(date);
		return new ResponseEntity<List<Orden>>(ordens, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna la orden segun su Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Orden> listarId(@PathVariable("id") Integer id) {
		Optional<Orden> orden = ordenService.listId(id);
		if (!orden.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Orden>(orden.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Registra una orden")
	@PostMapping
	public ResponseEntity<Orden> registrar(@Valid @RequestBody Orden orden){
		Orden NewOrden = new Orden();
		NewOrden = ordenService.registrar(orden);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(NewOrden.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	/*
	@ApiOperation("Registrar pedidos con una orden asociada")
	@PostMapping
	public ResponseEntity<Orden> registrarPedidos(@Valid @RequestBody Orden orden) {
		Orden NewOrden = new Orden();
		NewOrden = ordenService.registrarPedidos(orden);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(NewOrden.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	*/
	@ApiOperation("Actualiza los datos de una orden")
	@PutMapping
	public ResponseEntity<Orden> actualizar(@Valid @RequestBody Orden orden) {		
		ordenService.modificar(orden);
		return new ResponseEntity<Orden>(HttpStatus.OK);
	}
	
	@ApiOperation("Elimina una orden segun el id brindado")
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		Optional<Orden> orden = ordenService.listId(id);
		if (!orden.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			ordenService.eliminar(id);
		}
	}
	
	@ApiOperation("Retorna la lista completa de ordenes activas")
	@GetMapping(value = "/activas/")
	public ResponseEntity<List<Orden>> listarActivas(){
		List<Orden> ordens = new ArrayList<>();
		ordens = ordenService.getActiveOrders();
		return new ResponseEntity<List<Orden>>(ordens, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna si la orden con el id dado está activa")
	@GetMapping(value = "/activas/{id}")
	public ResponseEntity<Integer> isActive(@PathVariable("id") Integer id) {
		Optional<Orden> orden = ordenService.listId(id);
		if (!orden.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Integer>(ordenService.isActive(orden.get().getId()), HttpStatus.OK);
	}
}