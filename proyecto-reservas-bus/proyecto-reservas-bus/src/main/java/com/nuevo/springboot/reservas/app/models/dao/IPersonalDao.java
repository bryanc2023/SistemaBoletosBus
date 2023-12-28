package com.nuevo.springboot.reservas.app.models.dao;


import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Personal;


public interface IPersonalDao extends CrudRepository<Personal, Integer>{

}
