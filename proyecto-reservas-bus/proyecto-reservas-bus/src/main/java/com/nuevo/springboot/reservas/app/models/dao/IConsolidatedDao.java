package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

public interface IConsolidatedDao<T> {

	void save(T entity);
    T findOne(Integer id);
    void delete(Integer id);
    List<T> findAll();
}
