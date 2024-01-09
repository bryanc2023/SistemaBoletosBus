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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Entity
@Table
public class Boleto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Integer id;
	

	
	@ManyToOne
	@JoinColumn(name="id_detalle")
	private Detalle detalle;
	
	@OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name = "id_asiento")
	private Asiento asiento;
	
	@ManyToOne
	@JoinColumn(name="id_cronograma")
	private Cronograma cronograma;
		
	 


	
	
	
	

	@Column(name = "metodo_pago")
	private String metodoPago;
	
	
	@Column(name = "total_Pago")
	private Float totalPago;
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}






	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}



	public Float getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(Float totalPago) {
		this.totalPago = totalPago;
	}

	

	


	

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public Boleto(Detalle detalle, Asiento asiento, Cronograma cronograma, String metodoPago, Float totalPago) {
		super();
		this.detalle = detalle;
		this.asiento = asiento;
		this.cronograma = cronograma;
		this.metodoPago = metodoPago;
		this.totalPago = totalPago;
	}

	public Boleto(Integer id, Detalle detalle, Asiento asiento, Cronograma cronograma, String metodoPago,
			Float totalPago) {
		super();
		this.id = id;
		this.detalle = detalle;
		this.asiento = asiento;
		this.cronograma = cronograma;
		this.metodoPago = metodoPago;
		this.totalPago = totalPago;
	}

	public Boleto(Integer id) {
		super();
		this.id = id;
	}

	public Boleto() {
		super();
	}


	
}	
	
	
	
	
	
	
	

	

