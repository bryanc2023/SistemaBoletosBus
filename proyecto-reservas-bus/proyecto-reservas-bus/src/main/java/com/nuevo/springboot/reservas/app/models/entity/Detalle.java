package com.nuevo.springboot.reservas.app.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table
public class Detalle {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	
	
	@OneToMany
	@JoinColumn(name = "id_detalle")
	private List<Boleto> boletos;
	
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
