package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;



public interface IAsientoService {
	public void save(Asiento entity);
    public Asiento findOne(Integer id);
    public void delete(Integer id);
    public List<Asiento> findAll();

}
