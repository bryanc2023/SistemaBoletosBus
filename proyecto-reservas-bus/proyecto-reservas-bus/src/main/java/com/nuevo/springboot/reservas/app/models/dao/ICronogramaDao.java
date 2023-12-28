package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Cronograma;

public interface ICronogramaDao extends CrudRepository<Cronograma, Integer>{
	

}
