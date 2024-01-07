package com.nuevo.springboot.reservas.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.ICronogramaDao;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;


@Service
public class CronogramaService implements ICronogramaService{
	

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

	@Override
	public Cronograma save1(Cronograma cronograma) {
		return cronogramaDao.save(cronograma);
	}


	@Override
	@Transactional(readOnly=true)
	public Cronograma findById(Integer id) {
		return cronogramaDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete1(Integer id) {
		cronogramaDao.deleteById(id);
	}

	@Override
	public boolean existsByFechaAndUnidadAndHoraSalida(Date fecha, Unidad unidad, String horaSalida) {
		 return cronogramaDao.existsByFechaAndUnidadAndHoraSalida(fecha, unidad, horaSalida);
	}

}
