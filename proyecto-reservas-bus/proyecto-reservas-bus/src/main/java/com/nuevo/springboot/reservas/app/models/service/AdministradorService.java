package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IAdministradorDao;
import com.nuevo.springboot.reservas.app.models.entity.Administrador;


@Service
public class AdministradorService implements GenericDataService <Administrador>{

	@Autowired
	private IAdministradorDao administradorDao;
	
	@Override
	@Transactional
	public void save(Administrador administrador) {
		administradorDao.save(administrador);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Administrador findOne(Integer id) {
		return administradorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		administradorDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> findAll() {
		return (List<Administrador>) administradorDao.findAll();
	}
}
