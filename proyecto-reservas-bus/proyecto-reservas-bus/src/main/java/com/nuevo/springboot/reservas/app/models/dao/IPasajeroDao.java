package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Pasajero;


public interface IPasajeroDao {
	public List<Pasajero> findAll();

	public void save(Pasajero pasajero);
	
	public Pasajero findOne(Integer id);
	
	public void delete(Integer id);
	
}
