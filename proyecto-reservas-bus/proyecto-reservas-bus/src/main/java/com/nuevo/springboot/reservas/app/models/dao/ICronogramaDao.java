package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;



import com.nuevo.springboot.reservas.app.models.entity.Cronograma;

public interface ICronogramaDao extends JpaRepository<Cronograma, Integer>{
	

}
