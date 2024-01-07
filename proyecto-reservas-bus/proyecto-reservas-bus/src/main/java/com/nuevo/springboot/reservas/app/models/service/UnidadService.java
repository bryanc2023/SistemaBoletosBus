package com.nuevo.springboot.reservas.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IUnidadDao;
import com.nuevo.springboot.reservas.app.models.entity.Asiento;
import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

@Service
public class UnidadService implements  IUnidadService{
	

	@Autowired
	private IUnidadDao unidadDao;
	
	@Override
	@Transactional
	  public void save(Unidad unidad, Integer cantidad) {
        int maxFilas = 4; // Máximo de filas
        int asientosPorFila = (int) Math.ceil((double) cantidad / maxFilas); // Asientos por fila
        int fila = 1;
        char columna = 'A';

        List<Asiento> asientos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Asiento asiento = new Asiento();
            asiento.setUnidad(unidad);
            asiento.setFila(String.valueOf(fila)); // Establecer la fila del asiento
            asiento.setColumna(String.valueOf(columna)); // Establecer la columna del asiento
            asiento.setEstado("Disponible"); // Establecer el estado del asiento

            asientos.add(asiento);

            if ((i + 1) % asientosPorFila == 0) {
                fila++;
                columna = 'A';
            } else {
                columna++;
            }
        }

        unidad.setAsientos(asientos); // Asigna la lista de asientos a la unidad
        unidadDao.save(unidad);
    }

	@Override
	@Transactional(readOnly=true)
	public Unidad findOne(Integer id) {
		return unidadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		unidadDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Unidad> findAll() {
		return (List<Unidad>) unidadDao.findAll();
	}

	@Override
	@Transactional
	public Unidad save1(Unidad unidad) {
		return unidadDao.save(unidad);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Unidad findById(Integer id) {
		return unidadDao.findById(id).orElse(null);
	}

	@Override
	public void delete1(Integer id) {
		unidadDao.deleteById(id);
		
	}
	
	
	 @Override
	    public boolean existeUnidadConNumeroYCooperativa(Integer numero, Cooperativa cooperativa) {
	        Unidad unidadExistente = unidadDao.findByNumeroAndCooperativa(numero, cooperativa);
	        return unidadExistente != null;
	    }
	
	
}

