package com.upc.dto;

import com.upc.model.entity.Comida;

public class PedidoDTO {
	private Comida comida;
	private Integer cantidad;
	
	public Comida getComida() {
		return comida;
	}
	public void setComida(Comida comida) {
		this.comida = comida;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
