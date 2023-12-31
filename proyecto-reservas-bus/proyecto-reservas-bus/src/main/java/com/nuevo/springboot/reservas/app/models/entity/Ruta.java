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
	
	private String imagen;
	

	public Ruta(Integer id, Administrador administrador, List<Cronograma> cronogramas, String rutaOrigen,
			String rutaDestino, float costoRuta, String imagen) {
		super();
		this.id = id;
		this.administrador = administrador;
		this.cronogramas = cronogramas;
		this.rutaOrigen = rutaOrigen;
		this.rutaDestino = rutaDestino;
		this.costoRuta = costoRuta;
		this.imagen = imagen;
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
	
	
	public List<Cronograma> getCronogramas() {
		return cronogramas;
	}
	public void setCronogramas(List<Cronograma> cronogramas) {
		this.cronogramas = cronogramas;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	@Override
	public String toString() {
		return "Ruta [id=" + id + ", administrador=" + administrador + ", cronogramas=" + cronogramas + ", rutaOrigen="
				+ rutaOrigen + ", rutaDestino=" + rutaDestino + ", costoRuta=" + costoRuta + ", imagen=" + imagen + "]";
	}
	public Ruta get() {
		return null;
	}


	
	
	
	
	
}
