package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
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

@Entity
@Table
public class Boleto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="unidad_id")
	private Unidad unidad;
	
	private String dia;
	@Column(name = "fecha_viaje" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yy-mm-dd")
	private Date fechaViaje;
	
	@PrePersist
	public void prePersist() {
		fechaViaje=new Date();
	}
	
	@DateTimeFormat(pattern="hh:mm:ss")
	private String horaSalida;
	
	@Column(name = "numero_asiento")
	private Integer numeroAsiento;

	@Column(name = "metodo_pago")
	private String metodoPago;
	
	private Float descuento;
	
	@Column(name = "total_Pago")
	private Float totalPago;
	
	private Float iva;

	@Column(name = "stock_boletos")
	private boolean stockBoletos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Date getFechaViaje() {
		return fechaViaje;
	}

	public void setFechaViaje(Date fechaViaje) {
		this.fechaViaje = fechaViaje;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Integer getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(Integer numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public Float getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(Float totalPago) {
		this.totalPago = totalPago;
	}

	public Float getIva() {
		return iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public boolean getStockBoletos() {
		return stockBoletos;
	}

	public void setStockBoletos(boolean stockBoletos) {
		this.stockBoletos = stockBoletos;
	}

	public Boleto(Integer id,  Unidad unidad, String dia, Date fechaViaje, String horaSalida,
			Integer numeroAsiento, String metodoPago, Float descuento, Float totalPago, Float iva,
			boolean stockBoletos) {
		super();
		this.id = id;
		this.unidad = unidad;
		this.dia = dia;
		this.fechaViaje = fechaViaje;
		this.horaSalida = horaSalida;
		this.numeroAsiento = numeroAsiento;
		this.metodoPago = metodoPago;
		this.descuento = descuento;
		this.totalPago = totalPago;
		this.iva = iva;
		this.stockBoletos = stockBoletos;
	}
	
	public Boleto(Integer id) {
		super();
		this.id = id;
	}

	public Boleto() {
		super();
	
	}
	
	public Boleto(  Unidad unidad, String dia, Date fechaViaje, String horaSalida,
			Integer numeroAsiento, String metodoPago, Float descuento, Float totalPago, Float iva,
			boolean stockBoletos) {
		super();
		
	
		this.unidad = unidad;
		this.dia = dia;
		this.fechaViaje = fechaViaje;
		this.horaSalida = horaSalida;
		this.numeroAsiento = numeroAsiento;
		this.metodoPago = metodoPago;
		this.descuento = descuento;
		this.totalPago = totalPago;
		this.iva = iva;
		this.stockBoletos = stockBoletos;
	}
	
}	
	
	
	
	
	
	
	

	

