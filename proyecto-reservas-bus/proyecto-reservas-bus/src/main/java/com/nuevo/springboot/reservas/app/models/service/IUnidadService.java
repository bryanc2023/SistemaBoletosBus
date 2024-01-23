package com.nuevo.springboot.reservas.app.models.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface IUnidadService {

	public void save(Unidad entity);
    public Unidad findOne(Integer id);
    public void delete(Integer id);
    public List<Unidad> findAll();
	public Unidad save1(Unidad entity);
	public Unidad findById(Integer id);
	public void delete1(Integer id);
	
	public boolean existeUnidadConNumeroYCooperativa(Integer numero, Cooperativa cooperativa);
	
	//Buscar por fecha
	List<Object[]> findByCronogramaFecha(String fecha);
	//Unidades y cronograma

	List<Object[]> obtenerUnidadesConCronogramaYRuta();
	List<Object[]> obtenerUnidadesConCronogramaActual();
	public Optional<Unidad> get(Integer id);
	public void update(Unidad unidad);
}
