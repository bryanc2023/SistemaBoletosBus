package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;


public interface IAsientoDao extends CrudRepository<Asiento, Integer>{

}
