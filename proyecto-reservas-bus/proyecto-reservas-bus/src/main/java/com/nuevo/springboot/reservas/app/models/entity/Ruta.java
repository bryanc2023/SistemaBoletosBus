package com.nuevo.springboot.reservas.app.models.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	private List<Cronograma> cronogramas;
	
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
