package com.nuevo.springboot.reservas.app.models.dao;


import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Pasajero;


public interface IPasajeroDao extends CrudRepository<Pasajero, Integer>{

}
