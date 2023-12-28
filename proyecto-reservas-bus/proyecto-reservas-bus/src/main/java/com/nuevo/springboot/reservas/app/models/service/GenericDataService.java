package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

public interface GenericDataService <T> {

	public void save(T entity);
    public T findOne(Integer id);
    public void delete(Integer id);
    public List<T> findAll();
}
