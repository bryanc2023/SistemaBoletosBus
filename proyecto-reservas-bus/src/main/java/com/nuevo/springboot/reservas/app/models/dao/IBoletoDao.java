package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;


public interface IBoletoDao {

	public List<Boleto> findAll();

	public void save(Boleto boleto);

	public Boleto findOne(Integer id);
	
	public void delete(Integer id);
}
