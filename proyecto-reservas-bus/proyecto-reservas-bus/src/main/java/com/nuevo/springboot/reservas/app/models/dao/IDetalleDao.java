package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nuevo.springboot.reservas.app.models.entity.Detalle;

public interface IDetalleDao extends JpaRepository<Detalle, Integer>{

}
