package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
    
    Object[] obtenerCronogramaPorId(Integer idCronograma);
    
    List<Asiento> findByIds(List<Integer> idsAsientos);
    
   
   
    List<Asiento> countByEstado(Integer cronogramaId);
    void updateEstadoToDisponible(Integer cronogramaId);
    
    double obtenerCostoRutaPorCronogramaId(Integer cronogramaId);
    
    double obtenerCostoTotal(Integer cronogramaId);
    
    double obtenerSubtotal(Integer cronogramaId);
}
