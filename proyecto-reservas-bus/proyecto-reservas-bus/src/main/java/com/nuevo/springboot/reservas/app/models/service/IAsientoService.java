package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;



public interface IAsientoService {
	public void save(Asiento entity);
    public Asiento findOne(Integer id);
    public void delete(Integer id);
    public List<Asiento> findAll();
    
    List<Asiento> findByCronogramaId(Integer cronogramaId);
    List<Asiento> findByCronogramaId1(Integer cronogramaId);
    List<Asiento> findByCronogramaId2(Integer cronogramaId);
    List<Asiento> findByCronogramaId3(Integer cronogramaId);
    List<Asiento> findByCronogramaId4(Integer cronogramaId);
}
