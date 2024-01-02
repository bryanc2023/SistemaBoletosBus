package com.nuevo.springboot.reservas.app.models.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IRutaDao;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;


@Service
public class RutaService implements  IRutaService{
	

	@Autowired
	private IRutaDao rutaDao;
	
	@Override
	@Transactional
	public void save(Ruta ruta) {
		rutaDao.save(ruta);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Ruta findOne(Integer id) {
		return rutaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		rutaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ruta> findAll() {
		return (List<Ruta>) rutaDao.findAll();
	}

	@Override
	public Ruta save1(Ruta ruta) {
	
		return rutaDao.save(ruta);
	}

	@Override
	@Transactional(readOnly=true)
	public Ruta findById(Integer id) {
		return rutaDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete1(Integer id) {
		rutaDao.deleteById(id);
	}

	@Override
	public Ruta get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ruta> duplicados() {
		List<Ruta> rutas = findAll(); // Obtener tu lista de rutas desde tu servicio o repositorio

		// Usar un HashSet para eliminar duplicados basados en rutaOrigen
		Set<String> origenesUnicos = new HashSet<>();
		List<Ruta> rutasSinDuplicados = new ArrayList<>();

		for (Ruta ruta : rutas) {
		    if (origenesUnicos.add(ruta.getRutaOrigen())) {
		        rutasSinDuplicados.add(ruta);
		    }
		}
		return rutasSinDuplicados;
	}

	@Override
	public List<Ruta> duplicados2() {
		List<Ruta> rutas = findAll(); // Obtener tu lista de rutas desde tu servicio o repositorio

		// Usar un HashSet para eliminar duplicados basados en rutaOrigen
		Set<String> destinosUnicos = new HashSet<>();
		List<Ruta> rutasSinDuplicados = new ArrayList<>();

		for (Ruta ruta : rutas) {
		    if (destinosUnicos.add(ruta.getRutaDestino())) {
		        rutasSinDuplicados.add(ruta);
		    }
		}
		return rutasSinDuplicados;
	}
	
	// Método para obtener rutas por origen y destino con cronograma
    @Override
    @Transactional(readOnly = true)
    public List<Ruta> findByOrigenAndDestino(String rutaOrigen, String rutaDestino) {
        return rutaDao.findByRutaOrigenAndRutaDestino(rutaOrigen, rutaDestino);
    }
}

