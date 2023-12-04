package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface IUnidadDao {

	public List<Unidad> findAll();

	public void save(Unidad unidad);
	
	public Unidad findOne(Integer id);
	
	public void delete(Integer id);
}
