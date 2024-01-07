package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IUnidadDao;
import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

@Service
public class UnidadService implements  IUnidadService{
	

	@Autowired
	private IUnidadDao unidadDao;
	
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
	
	
	 @Override
	    public boolean existeUnidadConNumeroYCooperativa(Integer numero, Cooperativa cooperativa) {
	        Unidad unidadExistente = unidadDao.findByNumeroAndCooperativa(numero, cooperativa);
	        return unidadExistente != null;
	    }
	
	
}

