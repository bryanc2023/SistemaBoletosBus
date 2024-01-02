package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;
import java.util.List;

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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Table
@Entity
public class Administrador{

	
	@Id
    private Integer id; // Esta será la clave primaria que también será clave foránea a id_usuario

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario_administrador")
    private Usuario usuario;
    
	
	  
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "genero")
	private String genero;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-mm-dd")
	@Column (name="fecha_ingreso")
	private Date fechaIngreso;
	
	@OneToMany
	@JoinColumn(name = "id_usuario_administrador")
    private List<Ruta> rutas;
	
	
	
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
	public List<Ruta> getRutas() {
		return rutas;
	}
	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Administrador(String nombre, String apellido, String genero, Date fechaIngreso) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.fechaIngreso = fechaIngreso;
	}

	public Administrador() {
		super();
	}
	public Administrador(Integer id, Usuario usuario, String nombre, String apellido, String genero, Date fechaIngreso,
			List<Ruta> rutas) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.fechaIngreso = fechaIngreso;
		this.rutas = rutas;
	}
	public Administrador(Integer id) {
		super();
		this.id = id;
	}


	

}
