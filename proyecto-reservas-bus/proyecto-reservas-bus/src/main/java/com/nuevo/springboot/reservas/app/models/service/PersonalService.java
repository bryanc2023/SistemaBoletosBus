package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IPersonalDao;

import com.nuevo.springboot.reservas.app.models.entity.Personal;

@Service
public class PersonalService implements  IPersonalService{
	

	@Autowired
	private IPersonalDao personalDao;
	
	@Override
	@Transactional
	public void save(Personal personal) {
		personalDao.save(personal);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Personal findOne(Integer id) {
		return personalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		personalDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Personal> findAll() {
		return (List<Personal>) personalDao.findAll();
	}

	@Override
	public Personal save1(Personal personal) {
		return personalDao.save(personal);
	}

	@Override
	@Transactional(readOnly=true)
	public Personal findById(Integer id) {
		return personalDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete1(Integer id) {
		personalDao.deleteById(id);
	}
}
