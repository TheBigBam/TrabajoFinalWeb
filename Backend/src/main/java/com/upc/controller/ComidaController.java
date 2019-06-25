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
import com.upc.model.entity.Comida;
import com.upc.service.ComidaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comidas")
public class ComidaController {
	@Autowired
	private ComidaService comidaService;
	
	@ApiOperation("Retorna una lista de comidas")
	@GetMapping
	public ResponseEntity<List<Comida>> listar(){
		List<Comida> comidas = new ArrayList<>();
		comidas = comidaService.listar();
		return new ResponseEntity<List<Comida>>(comidas, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna la comida correspondiente al id dado")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comida> listarId(@PathVariable("id") Integer id) {
		Optional<Comida> comida = comidaService.listId(id);
		if (!comida.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Comida>(comida.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Registra una comida")
	@PostMapping
	public ResponseEntity<Comida> registrar(@Valid @RequestBody Comida comida){
		Comida comidaNew = new Comida();
		comidaNew = comidaService.registrar(comida);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comidaNew.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation("Actualiza una comida")
	@PutMapping
	public ResponseEntity<Comida> actualizar(@Valid @RequestBody Comida comida) {		
		comidaService.modificar(comida);
		return new ResponseEntity<Comida>(HttpStatus.OK);
	}
	
	@ApiOperation("Elimina la comida correspondiente al id dado")
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		Optional<Comida> comida = comidaService.listId(id);
		if (!comida.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			comidaService.eliminar(id);
		}
	}
}