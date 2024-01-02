package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface IUnidadDao extends JpaRepository<Unidad, Integer>{

	
}
