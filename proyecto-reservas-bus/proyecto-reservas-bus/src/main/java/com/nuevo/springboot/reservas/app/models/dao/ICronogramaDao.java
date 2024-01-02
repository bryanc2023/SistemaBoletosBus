package com.nuevo.springboot.reservas.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface ICronogramaDao extends JpaRepository<Cronograma, Integer>{
	
}
