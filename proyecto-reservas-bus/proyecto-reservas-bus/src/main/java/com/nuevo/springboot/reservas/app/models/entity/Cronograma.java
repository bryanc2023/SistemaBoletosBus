package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

	private String dia;
	private String mes;
	private int anio;
	
	@ManyToOne
	@JoinColumn(name="id_unidad")
	private Unidad unidad;
	
	@ManyToOne
	@JoinColumn(name="id_ruta")
	private Ruta ruta;
	
	
	@PrePersist
	public void prePersist() {
		fecha=new Date();
	}
	
	
	
	public Cronograma(Integer id, String descripcion, Date fecha, String dia, String mes, int anio) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	public Cronograma(String descripcion, Date fecha, String dia, String mes, int anio) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	public Cronograma(Integer id) {
		super();
		this.id = id;
	}

	public Cronograma() {
		super();
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
