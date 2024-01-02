package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Administrador;

public interface IAdministradorService {

	public void save(Administrador entity);
    public Administrador findOne(Integer id);
    public void delete(Integer id);
    public List<Administrador> findAll();
	public Administrador save1(Administrador entity);
	public Administrador findById(Integer id);
	public void delete1(Integer id);

}
