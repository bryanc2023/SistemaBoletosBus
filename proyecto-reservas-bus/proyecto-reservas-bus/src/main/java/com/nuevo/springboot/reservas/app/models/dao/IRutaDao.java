package com.nuevo.springboot.reservas.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;

public interface IRutaDao extends JpaRepository<Ruta, Integer>{

	@Query("SELECT r FROM Ruta r " +
            "WHERE r.rutaOrigen = :rutaOrigen " +
            "AND r.rutaDestino = :rutaDestino " 
            )
    List<Ruta> findByRutaOrigenAndRutaDestino(@Param("rutaOrigen") String rutaOrigen, @Param("rutaDestino") String rutaDestino);
}
