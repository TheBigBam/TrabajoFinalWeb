package com.upc.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upc.model.entity.Menu;

@Service
public interface MenuService extends CrudService<Menu>{
	List<Menu> getMenudelDia(Date date);
}
