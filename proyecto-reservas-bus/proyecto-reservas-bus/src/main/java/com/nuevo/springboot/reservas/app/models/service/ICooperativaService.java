package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;

public interface ICooperativaService {

	public void save(Cooperativa entity);
    public Cooperativa findOne(Integer id);
    public void delete(Integer id);
    public List<Cooperativa> findAll();
	public Cooperativa save1(Cooperativa entity);
	public Cooperativa findById(Integer id);
	public void delete1(Integer id);
	
}
