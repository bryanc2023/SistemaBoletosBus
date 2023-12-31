package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;



import com.nuevo.springboot.reservas.app.models.entity.Ruta;

public interface IRutaDao extends JpaRepository<Ruta, Integer>{


}
