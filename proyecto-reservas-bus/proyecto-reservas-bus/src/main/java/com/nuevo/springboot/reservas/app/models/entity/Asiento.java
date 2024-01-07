package com.nuevo.springboot.reservas.app.models.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table
public class Asiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_unidad")
	private Unidad unidad;
	
	@OneToOne(mappedBy = "asiento", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	private Boleto boleto;
	
	@ManyToOne
	@JoinColumn(name="id_cronograma")
	private Cronograma cronograma;
	
	@Column(name = "fila")
	private String fila;
	
	@Column(name = "columna")
	private String columna;
	
	@Column(name = "estado", nullable = false)
	private String estado;

	
	
	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getColumna() {
		return columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Asiento(Integer id) {
		super();
		this.id = id;
	}



	public Asiento(Unidad unidad, Boleto boleto, Cronograma cronograma, String fila, String columna, String estado) {
		super();
		this.unidad = unidad;
		this.boleto = boleto;
		this.cronograma = cronograma;
		this.fila = fila;
		this.columna = columna;
		this.estado = estado;
	}

	public Asiento(Integer id, Unidad unidad, Boleto boleto, Cronograma cronograma, String fila, String columna,
			String estado) {
		super();
		this.id = id;
		this.unidad = unidad;
		this.boleto = boleto;
		this.cronograma = cronograma;
		this.fila = fila;
		this.columna = columna;
		this.estado = estado;
	}

	public Asiento() {
		super();
	}

	
	
	

}
