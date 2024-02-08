package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IDetalleDao;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;


@Service
public class DetalleService implements  IDetalleService{

	@Autowired
	private IDetalleDao detalleDao;
	
	@Override
	@Transactional
	public void save(Detalle detalle) {
		detalleDao.save(detalle);
		
	}

	/* Funciones: Funciones CRUD*/
	/* Descripcion: Funciones de busqueda, guardar, editar y eliminar */
	@Override
	@Transactional(readOnly=true)
	public Detalle findOne(Integer id) {
		return detalleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		detalleDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Detalle> findAll() {
		return (List<Detalle>) detalleDao.findAll();
	}

	@Override
	@Transactional
	public Detalle save1(Detalle detalle) {
		
		return detalleDao.save(detalle);
	}

	@Override
	@Transactional(readOnly=true)
	public Detalle findById(Integer id) {
		return detalleDao.findById(id).orElse(null);
	}
	
	
	@Override
	public void delete1(Integer id) {
		detalleDao.deleteById(id);
	}
}
