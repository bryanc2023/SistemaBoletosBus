package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Table
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	@Column(name = "correo_usuario")
	private String correoUsuario;
	@Column(name = "contraseña_usuario")
	private String contraseñaUsuario;
	@Column(name = "estado_actividad")
	private String estadoActividad;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public String getContraseñaUsuario() {
		return contraseñaUsuario;
	}
	public void setContraseñaUsuario(String contraseñaUsuario) {
		this.contraseñaUsuario = contraseñaUsuario;
	}
	public String getEstadoActividad() {
		return estadoActividad;
	}
	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}
	public Usuario(Integer id, String nombreUsuario, String correoUsuario, String contraseñaUsuario,
			String estadoActividad) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.correoUsuario = correoUsuario;
		this.contraseñaUsuario = contraseñaUsuario;
		this.estadoActividad = estadoActividad;
	}
	public Usuario(String nombreUsuario, String correoUsuario, String contraseñaUsuario, String estadoActividad) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correoUsuario = correoUsuario;
		this.contraseñaUsuario = contraseñaUsuario;
		this.estadoActividad = estadoActividad;
	}
	public Usuario() {
		super();
	}
	public Usuario(Integer id) {
		super();
		this.id = id;
	}


}
