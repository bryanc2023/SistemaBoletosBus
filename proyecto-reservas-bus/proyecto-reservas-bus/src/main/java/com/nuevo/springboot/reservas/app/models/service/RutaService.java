package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IRutaDao;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;

@Service
public class RutaService implements  GenericDataService <Ruta>{
	

	@Autowired
	private IRutaDao rutaDao;
	
	@Override
	@Transactional
	public void save(Ruta ruta) {
		rutaDao.save(ruta);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Ruta findOne(Integer id) {
		return rutaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		rutaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ruta> findAll() {
		return (List<Ruta>) rutaDao.findAll();
	}

}

