package com.nuevo.springboot.reservas.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.nuevo.springboot.reservas.app.models.entity.Boleto;


public interface IBoletoDao extends JpaRepository<Boleto, Integer>{
	
	@Query("SELECT a FROM Boleto a WHERE a.usuario.id = :usuarioId AND a.cronograma.id= :cronogramaId")
	List<Boleto> findByIdUsuarioCronograma(@Param("usuarioId") Long  usuarioId,@Param("cronogramaId") Integer  cronogramaId);
	
	@Query("SELECT a FROM Boleto a WHERE a.usuario.id = :usuarioId ")
	List<Boleto> findByIdUsuario(@Param("usuarioId") Long  usuarioId);
	
	 @Query("SELECT a FROM Boleto a JOIN a.asiento b WHERE b.unidad.id = :unidadId")
	 List<Boleto> findByUnidadId(@Param("unidadId") Integer unidadId);
}
