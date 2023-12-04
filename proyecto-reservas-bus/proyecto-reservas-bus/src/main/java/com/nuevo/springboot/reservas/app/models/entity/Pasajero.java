package com.nuevo.springboot.reservas.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Pasajero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String Nombre;
	private String Apellido;
	private String Telefono;
	private String Genero;
	private String Direccion;
	private String Edad;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public Pasajero(Integer id, String nombre, String apellido, String telefono,
			String genero, String direccion, String edad) {
		super();
		Id = id;
		Nombre = nombre;
		Apellido = apellido;
		Telefono = telefono;
		Genero = genero;
		Direccion = direccion;
		Edad = edad;
	}

	public Pasajero(String nombre, String apellido, String telefono,
			String genero, String direccion, String edad) {
		super();
		
		Nombre = nombre;
		Apellido = apellido;
		Telefono = telefono;
		Genero = genero;
		Direccion = direccion;
		Edad = edad;
	}
	
	public Pasajero(Integer id) {
		super();
		Id = id;
	}
	public Pasajero() {
		super();
	}

}
