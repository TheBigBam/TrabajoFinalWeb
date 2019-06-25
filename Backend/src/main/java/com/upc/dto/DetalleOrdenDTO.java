package com.upc.dto;

import java.util.List;

import com.upc.model.entity.Orden;

public class DetalleOrdenDTO {

	private Orden orden;
	
	private List<PedidoDTO> pedidos;

	
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}

	
}