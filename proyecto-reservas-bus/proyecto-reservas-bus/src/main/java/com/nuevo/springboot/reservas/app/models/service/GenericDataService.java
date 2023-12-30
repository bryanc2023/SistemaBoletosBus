package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;





public interface GenericDataService <T> {

	public void save(T entity);
    public T findOne(Integer id);
    public void delete(Integer id);
    public List<T> findAll();
	public T save1(T entity);
	public T findById(Integer id);
	public void delete1(Integer id);

}
