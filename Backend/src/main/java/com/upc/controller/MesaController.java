package com.upc.controller;

import java.net.URI;
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
import com.upc.model.entity.Mesa;
import com.upc.service.MesaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mesas")
public class MesaController {
	@Autowired
	private MesaService mesaService;
	
	@ApiOperation("Retorna el listado de mesas del restaurante")
	@GetMapping
	public ResponseEntity<List<Mesa>> listar(){
		List<Mesa> mesas = new ArrayList<>();
		mesas = mesaService.listar();
		return new ResponseEntity<List<Mesa>>(mesas, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna el listado de mesas disponibles")
	@GetMapping(value = "/disponibles")
	public ResponseEntity<List<Mesa>> listarMesasDisponibles(){
		List<Mesa> mesas = new ArrayList<>();
		mesas = mesaService.listaMesasDisponibles();
		return new ResponseEntity<List<Mesa>>(mesas, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna una mesa segun su Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Mesa> listarId(@PathVariable("id") Integer id) {
		Optional<Mesa> mesa = mesaService.listId(id);
		if (!mesa.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Mesa>(mesa.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Registra una mesa")
	@PostMapping
	public ResponseEntity<Mesa> registrar(@Valid @RequestBody Mesa mesa){
		Mesa mesaNew = new Mesa();
		mesaNew = mesaService.registrar(mesa);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mesaNew.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation("Actualiza los datos de una mesa")
	@PutMapping
	public ResponseEntity<Mesa> actualizar(@Valid @RequestBody Mesa mesa) {		
		mesaService.modificar(mesa);
		return new ResponseEntity<Mesa>(HttpStatus.OK);
	}
	
	@ApiOperation("Elimina una mesa segun su id")
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		Optional<Mesa> mesa = mesaService.listId(id);
		if (!mesa.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			mesaService.eliminar(id);
		}
	}
}