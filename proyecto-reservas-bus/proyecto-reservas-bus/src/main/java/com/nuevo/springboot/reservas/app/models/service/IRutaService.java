package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;

public interface IRutaService {
	

	public void save(Ruta entity);
    public Ruta findOne(Integer id);
    public void delete(Integer id);
    public List<Ruta> findAll();
	public Ruta save1(Ruta entity);
	public Ruta findById(Integer id);
	public Ruta get(Integer id);
	public void delete1(Integer id);
	List<Ruta> duplicados();
	List<Ruta> duplicados2();
	List<Ruta> findByOrigenAndDestino(String rutaOrigen, String rutaDestino);



}
