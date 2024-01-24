package com.nuevo.springboot.reservas.app.models.dao;




import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface IUnidadDao extends JpaRepository<Unidad, Integer>{

	Unidad findByNumeroAndCooperativa(Integer numero, Cooperativa cooperativa);

	@Query(value = "SELECT a.id,a.numero,c.nombre,b.fecha,b.hora_salida,d.ruta_origen,d.ruta_destino,b.id as id_cronograma,a.imagen FROM unidad as a right join cronograma as b on a.id=b.id_unidad inner join cooperativa as c on a.id_cooperativa=c.id inner join ruta as d on b.id_ruta=d.id_ruta where b.fecha=?1", nativeQuery = true)
    List<Object[]> obtenerUnidadesConCronogramaYRutaPorFecha(String fecha);
	  //Obtener todos
	@Query(value = "SELECT a.id,a.numero,c.nombre,b.fecha,b.hora_salida,d.ruta_origen,d.ruta_destino,b.id as id_cronograma,a.imagen FROM unidad as a right join cronograma as b on a.id=b.id_unidad inner join cooperativa as c on a.id_cooperativa=c.id inner join ruta as d on b.id_ruta=d.id_ruta", nativeQuery = true)
	List<Object[]> obtenerUnidadesConCronogramaYRuta();
	
	 //Obtener actuales
	@Query(value = "SELECT a.id, a.numero, c.nombre, b.fecha, b.hora_salida, d.ruta_origen, d.ruta_destino, b.id as id_cronograma,a.imagen FROM unidad as a right join cronograma as b on a.id = b.id_unidad inner join cooperativa as c on a.id_cooperativa = c.id inner join ruta as d on b.id_ruta = d.id_ruta WHERE DATE(b.fecha) = CURRENT_DATE", nativeQuery = true)
	List<Object[]> obtenerUnidadesConCronogramaActual();


}
