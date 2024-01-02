package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;

public interface IBoletoService {

	public void save(Boleto entity);
    public Boleto findOne(Integer id);
    public void delete(Integer id);
    public List<Boleto> findAll();
	public Boleto save1(Boleto entity);
	public Boleto findById(Integer id);
	public void delete1(Integer id);

}
