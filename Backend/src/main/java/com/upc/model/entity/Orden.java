package com.upc.model.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "mesa_id", nullable = false)
	private Mesa mesa;
	
	@ManyToOne
	@JoinColumn(name = "mesero_id", nullable = false)
	private Mesero mesero;
	
	@Column(name = "fecha_generacion", nullable = false)
	private Date fecha_generacion;
	
	@Column(name ="nro_clientes",nullable=false)
	private Integer nro_clientes;
	
	@OneToMany(mappedBy = "orden", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<DetalleOrden> detalle_orden;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Mesero getMesero() {
		return mesero;
	}

	public void setMesero(Mesero mesero) {
		this.mesero = mesero;
	}

	public Date getFecha_generacion() {
		return fecha_generacion;
	}

	public void setFecha_generacion(Date fecha_generacion) {
		this.fecha_generacion = fecha_generacion;
	}

	public Integer getNro_clientes() {
		return nro_clientes;
	}

	public void setNro_clientes(Integer nro_clientes) {
		this.nro_clientes = nro_clientes;
	}

	public List<DetalleOrden> getDetalle_orden() {
		return detalle_orden;
	}

	public void setDetalle_orden(List<DetalleOrden> detalle_orden) {
		this.detalle_orden = detalle_orden;
	}
}