package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;


public interface IDetalleDao {
	public List<Detalle> findAll();

	public void save(Detalle detalle);

	public Detalle findOne(Integer id);
	
	public void delete(Integer id);
}
