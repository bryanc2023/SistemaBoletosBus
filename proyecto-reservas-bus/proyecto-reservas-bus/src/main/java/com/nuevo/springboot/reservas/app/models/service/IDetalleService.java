package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;

public interface IDetalleService {

	public void save(Detalle entity);
    public Detalle findOne(Integer id);
    public void delete(Integer id);
    public List<Detalle> findAll();
	public Detalle save1(Detalle entity);
	public Detalle findById(Integer id);
	public void delete1(Integer id);
}
