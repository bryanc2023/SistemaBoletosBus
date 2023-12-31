package com.nuevo.springboot.reservas.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;



import com.nuevo.springboot.reservas.app.models.entity.Pasajero;


public interface IPasajeroDao extends JpaRepository<Pasajero, Integer>{

}
