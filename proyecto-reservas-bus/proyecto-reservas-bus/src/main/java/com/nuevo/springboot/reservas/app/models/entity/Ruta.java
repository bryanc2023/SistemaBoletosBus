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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.persistence.TemporalType;

@Table
@Entity
public class Ruta{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_ruta")
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario_administrador")
    private Administrador administrador;
	
	@OneToMany
	@JoinColumn(name = "id_ruta")
	public List<Cronograma> cronogramas;
	
	@Column (name="ruta_origen")
	private String rutaOrigen;
	@Column (name="ruta_destino")
	private String rutaDestino;
	@Column (name="costo_ruta")
	private float costoRuta;
	
	
	public Ruta(Integer id, String rutaOrigen, String rutaDestino, float costoRuta,Administrador administrador) {
		super();
		this.id = id;
		this.rutaOrigen = rutaOrigen;
		this.rutaDestino = rutaDestino;
		this.costoRuta = costoRuta;
		this.administrador=administrador;
	}
	public Ruta() {
		super();
	}
	public Ruta(Integer id) {
		super();
		this.id = id;
	}
	public Ruta(String rutaOrigen, String rutaDestino, float costoRuta, Administrador administrador) {
		super();
		this.rutaOrigen = rutaOrigen;
		this.rutaDestino = rutaDestino;
		this.costoRuta = costoRuta;
		this.administrador=administrador;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRutaOrigen() {
		return rutaOrigen;
	}
	public void setRutaOrigen(String rutaOrigen) {
		this.rutaOrigen = rutaOrigen;
	}
	public String getRutaDestino() {
		return rutaDestino;
	}
	public void setRutaDestino(String rutaDestino) {
		this.rutaDestino = rutaDestino;
	}
	public float getCostoRuta() {
		return costoRuta;
	}
	public void setCostoRuta(float costoRuta) {
		this.costoRuta = costoRuta;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	
	
	
	
	
}
