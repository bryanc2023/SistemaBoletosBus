package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.ICooperativaDao;
import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;


@Service
public class CooperativaService implements  ICooperativaService{
	

	@Autowired
	private ICooperativaDao cooperativaDao;
	
	/* Funciones: Funciones CRUD*/
	/* Descripcion: Funciones de busqueda, guardar, editar y eliminar */
	@Override
	@Transactional
	public void save(Cooperativa cooperativa) {
		cooperativaDao.save(cooperativa);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cooperativa findOne(Integer id) {
		return cooperativaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		cooperativaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cooperativa> findAll() {
		return (List<Cooperativa>) cooperativaDao.findAll();
	}

	@Override
	@Transactional
	public Cooperativa save1(Cooperativa cooperativa) {
		return cooperativaDao.save(cooperativa);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Cooperativa findById(Integer id) {
		return cooperativaDao.findById(id).orElse(null);
	}

	@Override
	public void delete1(Integer id) {
		cooperativaDao.deleteById(id);
		
	}

	

	
	
	
	
	
}

