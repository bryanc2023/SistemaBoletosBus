package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;

public interface ICronogramaDao {
	public List<Cronograma> findAll();

	public void save(Cronograma cronograma);

	public Cronograma findOne(Integer id);
	
	public void delete(Integer id);

}
