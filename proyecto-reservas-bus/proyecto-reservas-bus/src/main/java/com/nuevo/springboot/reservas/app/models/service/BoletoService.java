package com.nuevo.springboot.reservas.app.models.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IBoletoDao;
import com.nuevo.springboot.reservas.app.models.entity.Boleto;


@Service
public class BoletoService implements IBoletoService {

	@Autowired
	private IBoletoDao boletoDao;
	
	/* Funciones: Funciones CRUD*/
	/* Descripcion: Funciones de busqueda, guardar, editar y eliminar */
	@Override
	@Transactional
	public void save(Boleto boleto) {
		boletoDao.save(boleto);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Boleto findOne(Integer id) {
		return boletoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		boletoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Boleto> findAll() {
		return (List<Boleto>) boletoDao.findAll();
	}

	@Override
	public Boleto save1(Boleto boleto) {
		// TODO Auto-generated method stub
		return boletoDao.save(boleto);
	}



	@Override
	@Transactional(readOnly=true)
	public Boleto findById(Integer id) {
		return boletoDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete1(Integer id) {
		boletoDao.deleteById(id);
	}

	/* Funciones: Funciones Busqueda por ID*/
	/* Descripcion: Funciones de busqueda por ID del usuario */
	@Override
	public List<Boleto> findByIdUsuario(Long usuarioId) {
		
		return boletoDao.findByIdUsuario(usuarioId);
	}

	/* Funciones: Funciones Búsqueda por reserva en efectivo*/
	/* Descripcion: Funciones de busqueda realizada por las reservas que ha realizado en efectivo*/
	@Override
	public List<Boleto> findByIdUsuarioEfectivo(Long usuarioId) {
		
		return boletoDao.findByIdUsuarioEfectivo(usuarioId);
	}
	
	
	/* Funciones: Funciones Búsqueda por reserva en linea*/
	/* Descripcion: Funciones de busqueda realizada por las reservas que ha realizado en linea o tarjeta*/
	@Override
	public List<Boleto> findByIdUsuarioTarjeta(Long usuarioId) {
		
		return boletoDao.findByIdUsuarioTarjeta(usuarioId);
	}
	
	/* Funciones: Funciones Búsqueda Boleto por Unidad*/
	/* Descripcion: Funciones de busqueda realizada por el ID de la unidad*/
	@Override
	public List<Boleto> findByUnidadId(Integer unidadId) {
		
		return boletoDao.findByUnidadId(unidadId);
	}
	
	/* Funciones: Funciones Búsqueda de usuario por cronograma*/
	/* Descripcion: Funciones de busqueda realizada por el usuario de un cronograma especifico*/
	@Override
	public List<Boleto> findByIdUsuarioCronograma(Long usuarioId, Integer cronogramaId) {
		// TODO Auto-generated method stub
		return boletoDao.findByIdUsuarioCronograma(usuarioId,cronogramaId);
	}

	/* Funciones: Funciones para obtener boletos por metodo de pago del dia de hoy*/
	/* Descripcion: Funciones de busqueda realizada por las reservas que ha realizado en efectivo*/
	@Override
	public List<Boleto> getBoletosFechaActualMetodo() {
		LocalDate fechaActual = LocalDate.now();
        return boletoDao.findBoletosByFechaActualAndMetodoPago(fechaActual);
	}
	
	/* Funciones: Funciones Búsqueda de boletos del dia de hoy*/
	/* Descripcion: Funciones de busqueda realizada por todos los boletos de la fecha de hoy*/
	@Override
	public List<Boleto> getBoletosFechaActual() {
		LocalDate fechaActual = LocalDate.now();
        return boletoDao.findBoletosByFechaActual(fechaActual);
	}
	
	/* Funciones: Funciones Búsqueda de boletos por descuento*/
	/* Descripcion: Funciones de busqueda realizada para obtener los boletos con descuento de hoy*/
	@Override
	public List<Boleto> getBoletosFechaActualMetodoDescuento() {
		LocalDate fechaActual = LocalDate.now();
        return boletoDao.findBoletosByFechaActualAndMetodoPagoDescuento(fechaActual);
	}
	
	
	/* Funciones: Funciones Búsqueda de boletos por fecha y unidad*/
	/* Descripcion: Funciones de busqueda realizada para obtener los boletos de unidad y fecha*/
	@Override
	@Transactional(readOnly = true)
	public List<Boleto> findBoletosByUnidadIdAndFecha(Integer unidadId, LocalDate fecha) {
	    return boletoDao.findBoletosByUnidadIdAndFecha(unidadId, fecha);
	}
	
}
