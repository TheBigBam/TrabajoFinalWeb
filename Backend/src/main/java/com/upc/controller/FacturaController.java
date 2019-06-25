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
import com.upc.model.entity.Factura;
import com.upc.service.FacturaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
	@Autowired
	private FacturaService facturaService;
	
	@ApiOperation("Retorna una lista de facturas")
	@GetMapping
	public ResponseEntity<List<Factura>> listar(){
		List<Factura> facturas = new ArrayList<>();
		facturas = facturaService.listar();
		return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna una lista de facturas correspondiente a la fecha dada")
	@GetMapping(value = "/fecha/{date}")
	public ResponseEntity<List<Factura>> listarFecha(@PathVariable("date") Date date){
		List<Factura> facturas = new ArrayList<>();
		facturas = facturaService.getFacturasDia(date);
		return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna la factura correspondiente al id dado")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Factura> listarId(@PathVariable("id") Integer id) {
		Optional<Factura> factura = facturaService.listId(id);
		if (!factura.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Factura>(factura.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Registra una factura")
	@PostMapping
	public ResponseEntity<Factura> registrar(@Valid @RequestBody Factura factura){
		Factura facturaNew = new Factura();
		facturaNew = facturaService.registrar(factura);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(facturaNew.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation("Actualiza una factura")
	@PutMapping
	public ResponseEntity<Factura> actualizar(@Valid @RequestBody Factura factura) {		
		facturaService.modificar(factura);
		return new ResponseEntity<Factura>(HttpStatus.OK);
	}
	
	@ApiOperation("Elimina la factura correspondiente al id dado")
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		Optional<Factura> factura = facturaService.listId(id);
		if (!factura.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			facturaService.eliminar(id);
		}
	}
}