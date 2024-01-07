package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nuevo.springboot.reservas.app.models.entity.Asiento;


public interface IAsientoDao extends JpaRepository<Asiento, Integer>{

}
