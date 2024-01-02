package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IAsientoDao;
import com.nuevo.springboot.reservas.app.models.entity.Asiento;


@Service
public class AsientoService implements IAsientoService{
	
	@Autowired
	private IAsientoDao asientoDao;

	@Override
	@Transactional
	public void save(Asiento asiento) {
		asientoDao.save(asiento);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Asiento findOne(Integer id) {
		return asientoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		asientoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Asiento> findAll() {
		return (List<Asiento>) asientoDao.findAll();
	}

}
