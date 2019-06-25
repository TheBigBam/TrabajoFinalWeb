package com.upc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.model.entity.Menu;
import com.upc.model.repository.MenuRepository;
import com.upc.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuRepository menuRepository;

	@Override
	public Menu registrar(Menu t) {
		return menuRepository.save(t);
	}

	@Override
	public Menu modificar(Menu t) {
		return menuRepository.save(t);
	}

	@Override
	public void eliminar(int id) {
		menuRepository.deleteById(id);
	}

	@Override
	public Optional<Menu> listId(int id) {
		return menuRepository.findById(id);
	}

	@Override
	public List<Menu> listar() {
		return menuRepository.findAll();
	}
	
	@Override
	public List<Menu> getMenudelDia(Date date){
		return menuRepository.getMenudelDia(date);
	}

	
}
