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

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"numero", "id_cooperativa"}))
@Entity
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	
	@OneToMany(mappedBy = "unidad", cascade = CascadeType.REMOVE)
	public List<Cronograma> cronogramas;
	
	@OneToMany(mappedBy = "unidad", cascade =CascadeType.ALL)
	public List<Asiento> asientos;
	
	@ManyToOne
	@JoinColumn(name="id_cooperativa")
	private Cooperativa cooperativa;

	@Column
	private Integer numero;
	
	private String imagen;
	@Column(name = "estado_actividad")
	private String estadoActividad;
	@Column(name = "cantidad_asientos")
	private Integer cantidadAsientos;
	@Column(name = "stock_boletos")
	private boolean stockBoletos;


	
	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public List<Cronograma> getCronogramas() {
		return cronogramas;
	}

	public void setCronogramas(List<Cronograma> cronogramas) {
		this.cronogramas = cronogramas;
	}

	public Cooperativa getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}

	public boolean isStockBoletos() {
		return stockBoletos;
	}

	public void setStockBoletos(boolean stockBoletos) {
		this.stockBoletos = stockBoletos;
	}

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



	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Unidad(Integer id) {
		super();
		this.id = id;
	}

	

	public Unidad(List<Cronograma> cronogramas, List<Asiento> asientos, Cooperativa cooperativa,
			Integer numero, String estadoActividad, Integer cantidadAsientos, boolean stockBoletos) {
		super();
		
		this.cronogramas = cronogramas;
		this.asientos = asientos;
		this.cooperativa = cooperativa;
		this.numero = numero;
		this.estadoActividad = estadoActividad;
		this.cantidadAsientos = cantidadAsientos;
		this.stockBoletos = stockBoletos;
	}

	public Unidad(Integer id, List<Cronograma> cronogramas, List<Asiento> asientos,
			Cooperativa cooperativa, Integer numero, String estadoActividad, Integer cantidadAsientos,
			boolean stockBoletos) {
		super();
		this.id = id;
		this.cronogramas = cronogramas;
		this.asientos = asientos;
		this.cooperativa = cooperativa;
		this.numero = numero;
		this.estadoActividad = estadoActividad;
		this.cantidadAsientos = cantidadAsientos;
		this.stockBoletos = stockBoletos;
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

	@Override
	public String toString() {
		return "Unidad [id=" + id + ", cronogramas=" + cronogramas + ", asientos=" + asientos + ", cooperativa="
				+ cooperativa + ", numero=" + numero + ", imagen=" + imagen + ", estadoActividad=" + estadoActividad
				+ ", cantidadAsientos=" + cantidadAsientos + ", stockBoletos=" + stockBoletos + "]";
	}
	
	
	
}
