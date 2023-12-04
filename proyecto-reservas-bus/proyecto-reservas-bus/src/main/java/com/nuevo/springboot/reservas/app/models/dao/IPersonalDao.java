package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Personal;


public interface IPersonalDao {
	public List<Personal> findAll();

	public void save(Personal personal);

	public Personal findOne(Integer id);
	
	public void delete(Integer id);
}
