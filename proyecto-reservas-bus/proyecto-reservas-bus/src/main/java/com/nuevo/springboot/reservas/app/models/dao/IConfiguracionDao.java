package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevo.springboot.reservas.app.models.entity.Configuracion;


public interface IConfiguracionDao extends JpaRepository<Configuracion, Integer> {

	
	 Configuracion findByEstado(String estado);
	

}
