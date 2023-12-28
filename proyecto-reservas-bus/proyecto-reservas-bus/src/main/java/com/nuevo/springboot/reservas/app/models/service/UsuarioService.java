package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IUsuarioDao;

import com.nuevo.springboot.reservas.app.models.entity.Usuario;

@Service
public class UsuarioService implements  GenericDataService <Usuario>{
	

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional
	public void save(Usuario unidad) {
		usuarioDao.save(unidad);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		usuarioDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

}
