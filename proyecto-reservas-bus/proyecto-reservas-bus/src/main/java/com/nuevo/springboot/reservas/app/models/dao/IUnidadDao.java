package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface IUnidadDao extends CrudRepository<Unidad, Integer>{

	
}
