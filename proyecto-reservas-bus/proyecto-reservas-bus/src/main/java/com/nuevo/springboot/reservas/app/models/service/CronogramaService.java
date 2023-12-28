package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.ICronogramaDao;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;

@Service
public class CronogramaService implements GenericDataService <Cronograma>{
	

	@Autowired
	private ICronogramaDao cronogramaDao;
	
	@Override
	@Transactional
	public void save(Cronograma cronograma) {
		cronogramaDao.save(cronograma);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cronograma findOne(Integer id) {
		return cronogramaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		cronogramaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cronograma> findAll() {
		return (List<Cronograma>) cronogramaDao.findAll();
	}

}
