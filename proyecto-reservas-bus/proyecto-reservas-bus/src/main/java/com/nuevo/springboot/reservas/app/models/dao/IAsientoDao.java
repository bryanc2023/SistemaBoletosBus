package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;

public interface IAsientoDao extends JpaRepository<Asiento, Integer> {

	@Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId")
	List<Asiento> findByCronogramaId(@Param("cronogramaId") Integer cronogramaId);

	@Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '1'")
	List<Asiento> findByCronogramaId1(@Param("cronogramaId") Integer cronogramaId);

	@Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '2'")
	List<Asiento> findByCronogramaId2(@Param("cronogramaId") Integer cronogramaId);
	
	@Query("SELECT a FROM Asiento a WHERE a.id IN :idsAsientos")
    List<Asiento> findByIds(@Param("idsAsientos") List<Integer> idsAsientos);

	@Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '3'")
	List<Asiento> findByCronogramaId3(@Param("cronogramaId") Integer cronogramaId);

	@Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.fila = '4'")
	List<Asiento> findByCronogramaId4(@Param("cronogramaId") Integer cronogramaId);

	@Query(value = "SELECT a.numero, c.nombre, b.fecha, b.hora_salida, d.ruta_origen, d.ruta_destino, b.id as id_cronograma FROM unidad as a right join cronograma as b on a.id=b.id_unidad inner join cooperativa as c on a.id_cooperativa=c.id inner join ruta as d on b.id_ruta=d.id_ruta WHERE b.id = :idCronograma", nativeQuery = true)
	Object[] obtenerCronogramaPorId(@Param("idCronograma") Integer idCronograma);
	
	
	@Query("SELECT a FROM Asiento a WHERE a.cronograma.id = :cronogramaId AND a.estado = 'Reservado'")
	List<Asiento> countByEstado(@Param("cronogramaId") Integer cronogramaId);

	@Modifying
    @Query("UPDATE Asiento a SET a.estado = 'Disponible' WHERE a.cronograma.id = :cronogramaId AND a.estado = 'Reservado'")
    void updateEstadoToDisponible(@Param("cronogramaId") Integer cronogramaId);
	
	@Query(value = "SELECT c.costo_ruta FROM asiento a " +
            "INNER JOIN cronograma b ON a.id_cronograma = b.id " +
            "INNER JOIN ruta c ON b.id_ruta = c.id_ruta " +
            "WHERE a.id_cronograma = :cronogramaId " +
            "LIMIT 1", nativeQuery = true)
    double obtenerCostoRutaPorCronogramaId(@Param("cronogramaId") Integer cronogramaId);
}
