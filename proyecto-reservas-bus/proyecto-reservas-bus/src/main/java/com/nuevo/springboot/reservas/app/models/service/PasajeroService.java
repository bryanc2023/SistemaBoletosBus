package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IPasajeroDao;

import com.nuevo.springboot.reservas.app.models.entity.Pasajero;
import com.nuevo.springboot.reservas.app.models.entity.Personal;

@Service
public class PasajeroService implements  GenericDataService <Pasajero>{

	@Autowired
	private IPasajeroDao pasajeroDao;
	
	@Override
	@Transactional
	public void save(Pasajero pasajero) {
		pasajeroDao.save(pasajero);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Pasajero findOne(Integer id) {
		return pasajeroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		pasajeroDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pasajero> findAll() {
		return (List<Pasajero>) pasajeroDao.findAll();
	}

	@Override
	public Pasajero save1(Pasajero pasajero) {
	
		return pasajeroDao.save(pasajero);
	}

	@Override
	@Transactional(readOnly=true)
	public Pasajero findById(Integer id) {
		return pasajeroDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete1(Integer id) {
		pasajeroDao.deleteById(id);
	}
}
