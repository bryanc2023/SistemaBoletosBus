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
	@JoinColumn(name="id_unidad")
	private Unidad unidad;
	
	@ManyToOne
	@JoinColumn(name="id_detalle")
	private Detalle detalle;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_asiento")
	private Asiento asiento;
		
	 


	
	private String dia;
	@Column(name = "fecha_viaje" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-mm-dd")
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
	
	
	@Column(name = "total_Pago")
	private Float totalPago;
	

	
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

	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
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



	public Float getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(Float totalPago) {
		this.totalPago = totalPago;
	}

	

	


	public Boleto(Unidad unidad, Detalle detalle, Asiento asiento, String dia, Date fechaViaje, String horaSalida,
			Integer numeroAsiento, String metodoPago, Float totalPago) {
		super();
		this.unidad = unidad;
		this.detalle = detalle;
		this.asiento = asiento;
		this.dia = dia;
		this.fechaViaje = fechaViaje;
		this.horaSalida = horaSalida;
		this.numeroAsiento = numeroAsiento;
		this.metodoPago = metodoPago;
		this.totalPago = totalPago;
	}

	public Boleto(Integer id, Unidad unidad, Detalle detalle, Asiento asiento, String dia, Date fechaViaje,
			String horaSalida, Integer numeroAsiento, String metodoPago, Float totalPago) {
		super();
		this.id = id;
		this.unidad = unidad;
		this.detalle = detalle;
		this.asiento = asiento;
		this.dia = dia;
		this.fechaViaje = fechaViaje;
		this.horaSalida = horaSalida;
		this.numeroAsiento = numeroAsiento;
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
	
	
	
	
	
	
	

	

