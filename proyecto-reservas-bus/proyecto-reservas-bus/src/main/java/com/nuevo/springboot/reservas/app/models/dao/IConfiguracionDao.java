package com.nuevo.springboot.reservas.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuevo.springboot.reservas.app.models.entity.Configuracion;


public interface IConfiguracionDao extends JpaRepository<Configuracion, Integer> {

	
	 Configuracion findByEstado(String estado);
	  @Query("SELECT c FROM Configuracion c WHERE c.estado = 'activo'")
	    List<Configuracion> findAllConfiguracionesActivas();

}
