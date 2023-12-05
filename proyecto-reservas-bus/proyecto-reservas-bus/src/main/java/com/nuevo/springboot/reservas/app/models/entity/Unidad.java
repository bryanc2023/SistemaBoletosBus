package com.nuevo.springboot.reservas.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table
@Entity
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "unidad")
	private List<Boleto> boletos;
	
	@OneToMany(mappedBy = "unidad")
	private List<Cronograma> cronogramas;

	@Column
	private Integer numero;
	private String cooperativa;
	@Column(name = "estado_actividad")
	private String estadoActividad;
	@Column(name = "cantidad_asientos")
	private Integer cantidadAsientos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(String cooperativa) {
		this.cooperativa = cooperativa;
	}

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public Integer getCantidadAsientos() {
		return cantidadAsientos;
	}

	public void setCantidadAsientos(Integer cantidadAsientos) {
		this.cantidadAsientos = cantidadAsientos;
	}

	public List<Boleto> getBoletos() {
		return boletos;
	}

	public void setBoletos(List<Boleto> boletos) {
		this.boletos = boletos;
	}

	public Unidad(Integer id, List<Boleto> boletos, Integer numeroInteger, String cooperativa, String estadoActividad,
			Integer cantidadAsientos) {
		super();
		this.id = id;
		this.boletos = boletos;
		this.numero = numero;
		this.cooperativa = cooperativa;
		this.estadoActividad = estadoActividad;
		this.cantidadAsientos = cantidadAsientos;
	}

	public Unidad(Integer id) {
		super();
		this.id = id;
	}

	public Unidad(List<Boleto> boletos, Integer numeroInteger, String cooperativa, String estadoActividad,
			Integer cantidadAsientos) {
		super();

		this.boletos = boletos;
		this.numero = numero;
		this.cooperativa = cooperativa;
		this.estadoActividad = estadoActividad;
		this.cantidadAsientos = cantidadAsientos;
	}

	public Unidad() {
		super();
	}
}
