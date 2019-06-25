package com.upc.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="orden_id",nullable=false)
	private Orden orden;
	
	@Column(name = "monto_total",nullable=false)
	private Float monto_total;
	
	@Column(name="fecha_generacion",nullable=false)
	private Date fecha_generacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Float getMonto_total() {
		return monto_total;
	}

	public void setMonto_total(Float monto_total) {
		this.monto_total = monto_total;
	}

	public Date getFecha_generacion() {
		return fecha_generacion;
	}

	public void setFecha_generacion(Date fecha_generacion) {
		this.fecha_generacion = fecha_generacion;
	}
}
