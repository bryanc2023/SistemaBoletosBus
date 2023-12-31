package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Usuario;

public interface IUsuarioService {

	public void save(Usuario entity);
    public Usuario findOne(Integer id);
    public void delete(Integer id);
    public List<Usuario> findAll();
	public Usuario save1(Usuario entity);
	public Usuario findById(Integer id);
	public void delete1(Integer id);
}
