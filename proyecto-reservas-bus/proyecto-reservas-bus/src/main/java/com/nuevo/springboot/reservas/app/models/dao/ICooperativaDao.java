package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;



import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;


public interface ICooperativaDao extends JpaRepository<Cooperativa, Integer>{

}
