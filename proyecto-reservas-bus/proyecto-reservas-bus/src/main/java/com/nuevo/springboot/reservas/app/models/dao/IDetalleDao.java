package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;

public interface IDetalleDao extends CrudRepository<Detalle, Integer>{

}
