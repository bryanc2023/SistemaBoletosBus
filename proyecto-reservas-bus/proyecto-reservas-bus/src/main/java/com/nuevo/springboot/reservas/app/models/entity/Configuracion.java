package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.persistence.TemporalType;



@Table
@Entity
public class Configuracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yy-mm-dd")
	@Column (name="fecha_configuracion")
	private Date fechaConfiguracion;
	
	@Column (name="descuento")
	private float descuento;
	
	@Column (name="iva")
	private float iva;
	
	
	@Column (name="cantidad_venta_b")
	private int cantidadVentaB;
	
	



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFechaConfiguracion() {
		return fechaConfiguracion;
	}


	public void setFechaConfiguracion(Date fechaConfiguracion) {
		this.fechaConfiguracion = fechaConfiguracion;
	}


	public float getDescuento() {
		return descuento;
	}


	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}


	public float getIva() {
		return iva;
	}


	public void setIva(float iva) {
		this.iva = iva;
	}


	public int getCantidadVentaB() {
		return cantidadVentaB;
	}


	public void setCantidadVentaB(int cantidadVentaB) {
		this.cantidadVentaB = cantidadVentaB;
	}







	public Configuracion(Date fechaConfiguracion, float descuento, float iva, int cantidadVentaB) {
		super();
		this.fechaConfiguracion = fechaConfiguracion;
		this.descuento = descuento;
		this.iva = iva;
		this.cantidadVentaB = cantidadVentaB;
	}


	public Configuracion(Integer id, Date fechaConfiguracion, float descuento, float iva, int cantidadVentaB) {
		super();
		this.id = id;
		this.fechaConfiguracion = fechaConfiguracion;
		this.descuento = descuento;
		this.iva = iva;
		this.cantidadVentaB = cantidadVentaB;
	}


	public Configuracion(Integer id) {
		super();
		this.id = id;
	}


	public Configuracion() {
		super();
	}
	
	

}
