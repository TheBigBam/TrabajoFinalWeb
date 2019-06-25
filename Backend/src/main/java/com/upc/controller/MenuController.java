package com.upc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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
import com.upc.model.entity.Menu;
import com.upc.service.MenuService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/menus")
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	@ApiOperation("Retorna una lista de comidas")
	@GetMapping
	public ResponseEntity<List<Menu>> listar(){
		Date fecha=new Date();
		List<Menu> menus = new ArrayList<>();
		menus = menuService.getMenudelDia(fecha);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna una lista de comidas")
	@GetMapping(value= "/dia/{fecha}")
	public ResponseEntity<List<Menu>> listarpordia(Date fecha){
		List<Menu> menus = new ArrayList<>();
		menus = menuService.getMenudelDia(fecha);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@ApiOperation("Retorna la comida correspondiente al id dado")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Menu> listarId(@PathVariable("id") Integer id) {
		Optional<Menu> menu = menuService.listId(id);
		if (!menu.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Menu>(menu.get(), HttpStatus.OK);
	}
	
	@ApiOperation("Registra una comida")
	@PostMapping
	public ResponseEntity<Menu> registrar(@Valid @RequestBody Menu menu){
		Menu menuNew = new Menu();
		menuNew = menuService.registrar(menu);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(menuNew.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation("Actualiza una comida")
	@PutMapping
	public ResponseEntity<Menu> actualizar(@Valid @RequestBody Menu menu) {		
		menuService.modificar(menu);
		return new ResponseEntity<Menu>(HttpStatus.OK);
	}
	
	@ApiOperation("Elimina la comida correspondiente al id dado")
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		Optional<Menu> comida = menuService.listId(id);
		if (!comida.isPresent()) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			menuService.eliminar(id);
		}
	}
}
