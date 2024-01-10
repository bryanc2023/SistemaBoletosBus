package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;

public interface IBoletoService {

	public void save(Boleto entity);
    public Boleto findOne(Integer id);
    public void delete(Integer id);
    public List<Boleto> findAll();
	public Boleto save1(Boleto entity);
	public Boleto findById(Integer id);
	public void delete1(Integer id);
	
	
	List<Boleto> findByIdUsuario(Long  usuarioId);
	
	List<Boleto> findByUnidadId(Integer unidadId);
	
	List<Boleto> findByIdUsuarioCronograma(Long  usuarioId,Integer  cronogramaId);

}
