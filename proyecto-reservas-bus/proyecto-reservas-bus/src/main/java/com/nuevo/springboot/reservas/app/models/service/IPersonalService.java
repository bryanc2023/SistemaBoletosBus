package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Personal;

public interface IPersonalService {

	public void save(Personal entity);
    public Personal findOne(Integer id);
    public void delete(Integer id);
    public List<Personal> findAll();
	public Personal save1(Personal entity);
	public Personal findById(Integer id);
	public void delete1(Integer id);
}
