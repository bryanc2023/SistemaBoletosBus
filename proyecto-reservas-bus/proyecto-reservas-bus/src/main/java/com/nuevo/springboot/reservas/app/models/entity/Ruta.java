package com.nuevo.springboot.reservas.app.models.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table
@Entity
public class Ruta{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Column (name="ruta_origen")
	private String rutaOrigen;
	@Column (name="ruta_destino")
	private String rutaDestino;
	@Column (name="costo_ruta")
	private float costoRuta;
	
	
	public Ruta(Long id, String rutaOrigen, String rutaDestino, float costoRuta) {
		super();
		this.id = id;
		this.rutaOrigen = rutaOrigen;
		this.rutaDestino = rutaDestino;
		this.costoRuta = costoRuta;
	}
	public Ruta() {
		super();
	}
	public Ruta(Long id) {
		super();
		this.id = id;
	}
	public Ruta(String rutaOrigen, String rutaDestino, float costoRuta) {
		super();
		this.rutaOrigen = rutaOrigen;
		this.rutaDestino = rutaDestino;
		this.costoRuta = costoRuta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	
	
	
}
