package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface IUnidadService {

	public void save(Unidad entity);
    public Unidad findOne(Integer id);
    public void delete(Integer id);
    public List<Unidad> findAll();
	public Unidad save1(Unidad entity);
	public Unidad findById(Integer id);
	public void delete1(Integer id);
	
	public boolean existeUnidadConNumeroYCooperativa(Integer numero, Cooperativa cooperativa);
}
