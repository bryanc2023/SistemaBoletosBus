package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Administrador;

public interface IAdministradorDao {

	public List<Administrador> findAll();

	public void save(Administrador administrador);

	public Administrador findOne(Integer id);
	
	public void delete(Integer id);
}
