package com.nuevo.springboot.reservas.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

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
	private String Edad;
	
	



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

	public String getEdad() {
		return Edad;
	}

	public void setEdad(String edad) {
		Edad = edad;
	}

	public Pasajero( List<Boleto> boletos, String nombre, String apellido, String telefono,
			String genero, String direccion, String edad) {
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
			String genero, String direccion, String edad) {
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
