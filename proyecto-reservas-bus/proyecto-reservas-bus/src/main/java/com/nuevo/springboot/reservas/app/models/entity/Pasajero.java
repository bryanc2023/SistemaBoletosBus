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

@Entity
@Table

public class Pasajero {
	
	
	
	@Id
    private Integer id; // Esta será la clave primaria que también será clave foránea a id_usuario

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario_pasajero")
    private Usuario usuario;
	
	@OneToMany
	@JoinColumn(name = "id_usuario_pasajero")
	private List<Boleto> boletos;
	
	private String Nombre;
	private String Apellido;
	private String Telefono;
	private String Genero;
	private String Direccion;
	private int Edad;
	
	



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Boleto> getBoletos() {
		return boletos;
	}

	public void setBoletos(List<Boleto> boletos) {
		this.boletos = boletos;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	public Pasajero( List<Boleto> boletos, String nombre, String apellido, String telefono,
			String genero, String direccion, int edad) {
		super();
		this.boletos = boletos;
		Nombre = nombre;
		Apellido = apellido;
		Telefono = telefono;
		Genero = genero;
		Direccion = direccion;
		Edad = edad;
	}

	

	public Pasajero() {
		super();
	}

	public Pasajero(Integer id, Usuario usuario, List<Boleto> boletos, String nombre, String apellido, String telefono,
			String genero, String direccion, int edad) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.boletos = boletos;
		Nombre = nombre;
		Apellido = apellido;
		Telefono = telefono;
		Genero = genero;
		Direccion = direccion;
		Edad = edad;
	}

	public Pasajero(Integer id) {
		super();
		this.id = id;
	}
	
	

	
}
