package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nuevo.springboot.reservas.app.models.entity.Administrador;

public interface IAdministradorDao extends CrudRepository<Administrador, Integer>{

	
}
