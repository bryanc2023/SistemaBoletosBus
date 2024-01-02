package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Cronograma{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yy-mm-dd")
	private Date fecha;

	@DateTimeFormat(pattern="hh:mm:ss")
	private String horaSalida;
	
	public String getHoraSalida() {
		return horaSalida;
	}



	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	private String dia;
	private String mes;
	private int anio;
	
	@ManyToOne
	@JoinColumn(name="id_unidad")
	private Unidad unidad;
	
	@ManyToOne
	@JoinColumn(name="id_ruta")
	private Ruta ruta;
	
	
	public Unidad getUnidad() {
		return unidad;
	}



	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}



	public Ruta getRuta() {
		return ruta;
	}



	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}



	@PrePersist
	public void prePersist() {
		fecha=new Date();
	}
	
	
	
	

	public Cronograma() {
		super();
	}



	public Cronograma(Integer id) {
		super();
		this.id = id;
	}



	


	public Cronograma(Integer id, String descripcion, Date fecha, String horaSalida, String dia, String mes, int anio,
			Unidad unidad, Ruta ruta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.unidad = unidad;
		this.ruta = ruta;
	}



	public Cronograma(String descripcion, Date fecha, String horaSalida, String dia, String mes, int anio,
			Unidad unidad, Ruta ruta) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.unidad = unidad;
		this.ruta = ruta;
	}



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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	
	
}
