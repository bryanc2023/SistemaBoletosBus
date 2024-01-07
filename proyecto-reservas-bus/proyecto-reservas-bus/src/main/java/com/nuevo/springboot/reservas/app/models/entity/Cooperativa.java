package com.nuevo.springboot.reservas.app.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table (uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"}))
@Entity
public class Cooperativa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "cooperativa", cascade = CascadeType.REMOVE)
	public List<Unidad> unidades;
	
	@Column(name = "nombre")
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cooperativa(Integer id, List<Unidad> unidades, String nombre) {
		super();
		this.id = id;
		this.unidades = unidades;
		this.nombre = nombre;
	}

	public Cooperativa(List<Unidad> unidades, String nombre) {
		super();
		this.unidades = unidades;
		this.nombre = nombre;
	}

	public Cooperativa() {
		super();
	}

	public Cooperativa(Integer id) {
		super();
		this.id = id;
	}
	

}
