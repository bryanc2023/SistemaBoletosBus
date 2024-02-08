package com.nuevo.springboot.reservas.app.models.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IUnidadDao;
import com.nuevo.springboot.reservas.app.models.entity.Asiento;
import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

@Service
public class UnidadService implements  IUnidadService{
	

	@Autowired
	private IUnidadDao unidadDao;
	
	/* Funciones: Funciones CRUD*/
	/* Descripcion: Funciones de busqueda, guardar, editar y eliminar */
	@Override
	@Transactional
	  public void save(Unidad unidad) {
       
       
        unidadDao.save(unidad);
    }

	@Override
	@Transactional(readOnly=true)
	public Unidad findOne(Integer id) {
		return unidadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		unidadDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Unidad> findAll() {
		return (List<Unidad>) unidadDao.findAll();
	}

	@Override
	@Transactional
	public Unidad save1(Unidad unidad) {
		return unidadDao.save(unidad);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Unidad findById(Integer id) {
		return unidadDao.findById(id).orElse(null);
	}

	@Override
	public void delete1(Integer id) {
		unidadDao.deleteById(id);
		
	}
	
	/* Funciones: Funcion de validación*/
	/* Descripcion: Funciones de busqueda si existe una unidad con el mismo numero y cooperativa*/
	 @Override
	    public boolean existeUnidadConNumeroYCooperativa(Integer numero, Cooperativa cooperativa) {
	        Unidad unidadExistente = unidadDao.findByNumeroAndCooperativa(numero, cooperativa);
	        return unidadExistente != null;
	    }

	 /* Funciones: Funcion de búsqueda*/
		/* Descripcion: Funciones de busqueda por fecha */
	@Override
	public List<Object[]> findByCronogramaFecha(String fecha) {
		return unidadDao.obtenerUnidadesConCronogramaYRutaPorFecha(fecha);
	}

	/* Funciones: Funciones de búsqueda*/
	/* Descripcion: Funciones de busqueda para obtener unidades por cronograma y ruta*/
	@Override
	public List<Object[]>obtenerUnidadesConCronogramaYRuta() {
		return unidadDao.obtenerUnidadesConCronogramaYRuta();
	}

	@Override
	public Optional<Unidad> get(Integer id) {
		return unidadDao.findById(id);
	}

	@Override
	public void update(Unidad unidad) {
	
		unidadDao.save(unidad);
	}

	/* Funciones: Funcion de búsqueda */
	/* Descripcion: Funciones de busqueda para obtener unidades de la fecha actual*/
	@Override
	public List<Object[]> obtenerUnidadesConCronogramaActual() {
		return unidadDao.obtenerUnidadesConCronogramaActual();
	}

	
	
	
}

