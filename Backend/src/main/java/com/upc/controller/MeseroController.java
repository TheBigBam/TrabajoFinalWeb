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
import com.upc.model.entity.Mesero;
import com.upc.service.MeseroService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/meseros")
public class MeseroController {
	@Autowired
	private MeseroService meseroService;
	
	@ApiOperation("Retorna una lista de meseros")
	@GetMapping
	public ResponseEntity<List<Mesero>> listar(){
		List<Mesero> meseros = new ArrayList<>();
		meseros = meseroService.listar();
		return new ResponseEntity<List<Mesero>>(meseros, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna el mesero por su id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Mesero> listarId(@PathVariable("id") Integer id) {
		Optional<Mesero> mesero = meseroService.listId(id);
		if (!mesero.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Mesero>(mesero.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Registra un mesero")
	@PostMapping
	public ResponseEntity<Mesero> registrar(@Valid @RequestBody Mesero mesero){
		Mesero meseroNew = new Mesero();
		meseroNew = meseroService.registrar(mesero);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(meseroNew.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation("Actualiza la informacion de un mesero")
	@PutMapping
	public ResponseEntity<Mesero> actualizar(@Valid @RequestBody Mesero mesero) {		
		meseroService.modificar(mesero);
		return new ResponseEntity<Mesero>(HttpStatus.OK);
	}
	
	@ApiOperation("Elimina un mesero segun su Id")
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		Optional<Mesero> mesero = meseroService.listId(id);
		if (!mesero.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			meseroService.eliminar(id);
		}
	}
}