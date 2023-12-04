package com.nuevo.springboot.reservas.app.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Detalle {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Detalle(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public Detalle(Integer id) {
		super();
		this.id = id;
	}
	public Detalle(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	public Detalle() {
		super();
	}
	
}
