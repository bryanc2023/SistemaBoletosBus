package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Usuario;

public interface IUsuarioDao {

	public List<Usuario> findAll();

	public void save(Usuario usuario);

	public Usuario findOne(Integer id);
	
	public void delete(Integer id);
}
