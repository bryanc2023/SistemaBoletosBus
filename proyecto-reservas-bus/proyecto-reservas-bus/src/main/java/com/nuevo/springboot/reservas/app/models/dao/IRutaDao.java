package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;



public interface IRutaDao {

public List<Ruta> findAll();
	
	public void save(Ruta ruta);
	
	
	public void delete(Integer id);

	public Ruta findOne(Integer id);
}
