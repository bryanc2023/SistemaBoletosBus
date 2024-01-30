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
import javax.persistence.Transient;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_asiento")
	private Asiento asiento;
	
	@ManyToOne
	@JoinColumn(name="id_cronograma")
	private Cronograma cronograma;
		
	 
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;


	
	
	
	

	@Column(name = "metodo_pago")
	private String metodoPago;
	
	
	@Column(name = "total_Pago")
	private double totalPago;
	

	@Column(name = "estado")
	private String estado;
	
	@Column(name = "descuento")
	private String descuento;
	
	
	
	
	

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

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






	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}



	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
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

	

	public Boleto(Detalle detalle, Asiento asiento, Cronograma cronograma, Usuario usuario, String metodoPago,
			double totalPago,String estado) {
		super();
		this.detalle = detalle;
		this.asiento = asiento;
		this.cronograma = cronograma;
		this.usuario = usuario;
		this.metodoPago = metodoPago;
		this.totalPago = totalPago;
		this.estado = estado;
		this.descuento = "No";
	}

	public Boleto(Integer id, Detalle detalle, Asiento asiento, Cronograma cronograma, Usuario usuario,
			String metodoPago, double totalPago,String estado) {
		super();
		this.id = id;
		this.detalle = detalle;
		this.asiento = asiento;
		this.cronograma = cronograma;
		this.usuario = usuario;
		this.metodoPago = metodoPago;
		this.totalPago = totalPago;
		this.estado = estado;
		this.descuento = "No";
	}

	public Boleto(Integer id) {
		super();
		this.id = id;
	}

	public Boleto() {
		super();
	}


	@Transient
    private String totalPagoFormateado;

    // Resto de la clase...

    // Getter y Setter para el totalPago formateado
    public String getTotalPagoFormateado() {
        return totalPagoFormateado;
    }

    public void setTotalPagoFormateado(String totalPagoFormateado) {
        this.totalPagoFormateado = totalPagoFormateado;
    }
    
}	
	
	
	
	
	
	
	

	

