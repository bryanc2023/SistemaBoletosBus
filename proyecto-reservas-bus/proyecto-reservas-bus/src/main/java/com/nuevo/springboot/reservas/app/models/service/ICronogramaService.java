package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;

public interface ICronogramaService {

	public void save(Cronograma entity);
    public Cronograma findOne(Integer id);
    public void delete(Integer id);
    public List<Cronograma> findAll();
	public Cronograma save1(Cronograma entity);
	public Cronograma findById(Integer id);
	public void delete1(Integer id);

}
