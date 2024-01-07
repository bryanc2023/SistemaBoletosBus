package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;


public interface IAsientoDao extends JpaRepository<Asiento, Integer>{

	 @Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId")
	  List<Asiento> findByCronogramaId(@Param("cronogramaId") Integer cronogramaId);
	 
	 @Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '1'")
	  List<Asiento> findByCronogramaId1(@Param("cronogramaId") Integer cronogramaId);
	 
	 @Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '2'")
	  List<Asiento> findByCronogramaId2(@Param("cronogramaId") Integer cronogramaId);
	 
	 @Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '3'")
	  List<Asiento> findByCronogramaId3(@Param("cronogramaId") Integer cronogramaId);
	 
	 @Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '4'")
	  List<Asiento> findByCronogramaId4(@Param("cronogramaId") Integer cronogramaId);
}
