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

@Table
@Entity
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "unidad")
	private List<Boleto> boletos;
	
	@OneToMany(mappedBy = "unidad")
	public List<Cronograma> cronogramas;

	@Column
	private Integer numero;
	private String cooperativa;
	@Column(name = "estado_actividad")
	private String estadoActividad;
	@Column(name = "cantidad_asientos")
	private Integer cantidadAsientos;
	@Column(name = "stock_boletos")
	private boolean stockBoletos;


	
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

	public Unidad(Integer id, List<Boleto> boletos, Integer numero, String cooperativa, String estadoActividad,
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

	public Unidad(List<Boleto> boletos, Integer numero, String cooperativa, String estadoActividad,
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
	
	public String getRutaOrigen() {
	    if (cronogramas != null && !cronogramas.isEmpty()) {
	        return cronogramas.get(0).getRuta().getRutaOrigen();
	    }
	    return null;
	}
	
	public String getRutaDestino() {
	    if (cronogramas != null && !cronogramas.isEmpty()) {
	        return cronogramas.get(0).getRuta().getRutaDestino();
	    }
	    return null;
	}
	
	public Float getCostoRuta() {
	    if (cronogramas != null && !cronogramas.isEmpty()) {
	        return cronogramas.get(0).getRuta().getCostoRuta();
	    }
	    return null;
	}
}
