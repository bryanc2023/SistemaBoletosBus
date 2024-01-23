package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IConfiguracionDao;
import com.nuevo.springboot.reservas.app.models.dao.ICooperativaDao;
import com.nuevo.springboot.reservas.app.models.entity.Configuracion;
import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;


@Service
public class ConfiguracionService implements  IConfiguracionService{
	

	@Autowired
	private IConfiguracionDao configuracionDao;
	
	@Override
	@Transactional
	public void save(Configuracion configuracion) {
		configuracionDao.save(configuracion);
		
	}

	
	@Override
	@Transactional(readOnly=true)
	public Configuracion findOne(Integer id) {
		return configuracionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		configuracionDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Configuracion> findAll() {
		return (List<Configuracion>) configuracionDao.findAll();
	}

	@Override
	@Transactional
	public Configuracion save1(Configuracion configuracion) {
		return configuracionDao.save(configuracion);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Configuracion findById(Integer id) {
		return configuracionDao.findById(id).orElse(null);
	}

	@Override
	public void delete1(Integer id) {
		configuracionDao.deleteById(id);
		
	}

	@Override
	public Configuracion findByEstado(String estado) {
		return configuracionDao.findByEstado("Activo");
	}

	@Override
	public double obtenerIVA() {
        Configuracion configuracionActiva = configuracionDao.findByEstado("Activo");
        if (configuracionActiva != null) {
            return configuracionActiva.getIva();
        }
        return 0.0; // Otra acción en caso de que no se encuentre la configuración activa
    }

	@Override
	public double obtenerDescuento() {
        Configuracion configuracionActiva = configuracionDao.findByEstado("Activo");
        if (configuracionActiva != null) {
            return configuracionActiva.getDescuento();
        }
        return 0.0; // Otra acción en caso de que no se encuentre la configuración activa
    }
	
	public Configuracion findConfiguracionActiva() {
	    return configuracionDao.findByEstado("activo");
	}
	
	public List<Configuracion> findAllConfiguracionesActivas() {
        return configuracionDao.findAllConfiguracionesActivas();
    }


	@Override
	public int obtenerMax() {
		Configuracion configuracionActiva = configuracionDao.findByEstado("Activo");
        if (configuracionActiva != null) {
            return configuracionActiva.getCantidadVentaB();
        }
		return 0;
	}
	
}

