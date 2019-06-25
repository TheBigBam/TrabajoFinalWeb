package com.upc.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "meseros")
public class Mesero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres máximo")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres máximo")
	@Column(name = "apellido", nullable = false, length = 100)
	private String apellido;
	
	@Size(min = 8, max = 8, message = "El DNI debe contener exactamente 8 dígitos")
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;
	
	@Column(name="fecha_nacimiento", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
}