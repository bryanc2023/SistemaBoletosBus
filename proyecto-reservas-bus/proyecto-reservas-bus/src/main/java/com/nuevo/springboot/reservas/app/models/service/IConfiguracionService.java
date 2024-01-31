package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Configuracion;


public interface IConfiguracionService {

	public void save(Configuracion entity);
    public Configuracion findOne(Integer id);
    public void delete(Integer id);
    public List<Configuracion> findAll();
	public Configuracion save1(Configuracion entity);
	public Configuracion findById(Integer id);
	public void delete1(Integer id);
	
	 Configuracion findByEstado(String estado);
	 double obtenerIVA();
	 double obtenerDescuento();
	 
	 int obtenerMax();
	  List<Configuracion> findAllConfiguracionesActivas();
}
