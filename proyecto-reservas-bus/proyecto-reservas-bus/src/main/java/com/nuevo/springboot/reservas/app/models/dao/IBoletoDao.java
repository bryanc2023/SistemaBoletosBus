package com.nuevo.springboot.reservas.app.models.dao;



import java.time.LocalDate;
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
	
	@Query("SELECT a FROM Boleto a WHERE a.usuario.id = :usuarioId AND a.metodoPago = 'Efectivo'" )
	List<Boleto> findByIdUsuarioEfectivo(@Param("usuarioId") Long  usuarioId);
	@Query("SELECT a FROM Boleto a WHERE a.usuario.id = :usuarioId AND a.metodoPago = 'Tarjeta'")
	List<Boleto> findByIdUsuarioTarjeta(@Param("usuarioId") Long  usuarioId);
	
	 @Query("SELECT a FROM Boleto a JOIN a.asiento b WHERE b.unidad.id = :unidadId")
	 List<Boleto> findByUnidadId(@Param("unidadId") Integer unidadId);
	 @Query("SELECT b FROM Boleto b WHERE b.cronograma.fecha = :fechaActual ")
	    List<Boleto> findBoletosByFechaActual(@Param("fechaActual") LocalDate fechaActual);
	 
	 @Query("SELECT b FROM Boleto b WHERE b.cronograma.fecha = :fechaActual AND b.metodoPago = 'Efectivo' AND b.estado = 'Pendiente'")
	    List<Boleto> findBoletosByFechaActualAndMetodoPago(@Param("fechaActual") LocalDate fechaActual);
	 
	 @Query("SELECT b FROM Boleto b WHERE b.cronograma.fecha = :fechaActual AND b.metodoPago = 'Efectivo' AND b.descuento = 'Solicitado'")
	 List<Boleto> findBoletosByFechaActualAndMetodoPagoDescuento(@Param("fechaActual") LocalDate fechaActual);

	 @Query("SELECT b FROM Boleto b JOIN b.asiento a WHERE a.unidad.id = :unidadId AND b.cronograma.fecha = :fechaActual")
	 List<Boleto> findBoletosByUnidadIdAndFecha(@Param("unidadId") Integer unidadId, @Param("fechaActual") LocalDate fecha);

	 
}
