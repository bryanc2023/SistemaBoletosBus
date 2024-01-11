package com.nuevo.springboot.reservas.app.models.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;
import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

public interface ICronogramaService {

	public void save(Cronograma cronograma,Unidad unidad);
    public Cronograma findOne(Integer id);
    public void delete(Integer id);
    public List<Cronograma> findAll();
	public Cronograma save1(Cronograma entity);
	public Cronograma findById(Integer id);
	public void delete1(Integer id);
	
    //Validacion de fecha unidad y hora
	 boolean existsByFechaAndUnidadAndHoraSalida(LocalDate fecha, Unidad unidad, String horaSalida);
}
