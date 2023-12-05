package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table
@Entity
public class Personal {

	

	
	@Id
    private Integer id; // Esta será la clave primaria que también será clave foránea a id_usuario

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario_personal")
    private Usuario usuario;
	
	@OneToMany
	@JoinColumn(name = "id_usuario_personal")
	private List<Boleto> boletos;

	private String nombre;
	private String apellido;
	private String genero;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yy-mm-dd")
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;
	@Column(name = "nro_puesto")
	private int nroPuesto;

	@PrePersist
	public void prePersist() {
		fechaIngreso = new Date();
	}

	




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

	public int getNroPuesto() {
		return nroPuesto;
	}

	public void setNroPuesto(int nroPuesto) {
		this.nroPuesto = nroPuesto;
	}

	public Personal( List<Boleto> boletos, String nombre, String apellido, String genero,
			Date fechaIngreso, int nroPuesto) {
		super();
		this.boletos = boletos;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.fechaIngreso = fechaIngreso;
		this.nroPuesto = nroPuesto;
	}


	public Personal() {
		super();
	}






	public Personal(Integer id, Usuario usuario, List<Boleto> boletos, String nombre, String apellido, String genero,
			Date fechaIngreso, int nroPuesto) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.boletos = boletos;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.fechaIngreso = fechaIngreso;
		this.nroPuesto = nroPuesto;
	}






	public Personal(Integer id) {
		super();
		this.id = id;
	}


}
