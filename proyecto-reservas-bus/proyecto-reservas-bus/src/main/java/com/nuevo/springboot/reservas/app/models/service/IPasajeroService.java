package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Pasajero;

public interface IPasajeroService {

	public void save(Pasajero entity);
    public Pasajero findOne(Integer id);
    public void delete(Integer id);
    public List<Pasajero> findAll();
	public Pasajero save1(Pasajero entity);
	public Pasajero findById(Integer id);
	public void delete1(Integer id);
}
