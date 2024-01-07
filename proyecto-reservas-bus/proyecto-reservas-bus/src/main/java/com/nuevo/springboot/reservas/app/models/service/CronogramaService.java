package com.nuevo.springboot.reservas.app.models.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.ICronogramaDao;
import com.nuevo.springboot.reservas.app.models.entity.Asiento;
import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;


@Service
public class CronogramaService implements ICronogramaService{
	

	@Autowired
	private ICronogramaDao cronogramaDao;
	
	@Override
	@Transactional
	public void save(Cronograma cronograma,Unidad unidad) {
		 int maxFilas = 4; // Máximo de filas
		 int cantidad=unidad.getCantidadAsientos();
	        int asientosPorFila = (int) Math.ceil((double) cantidad / maxFilas); // Asientos por fila
	        int fila = 1;
	        char columna = 'A';
	       

	        List<Asiento> asientos = new ArrayList<>();
	        for (int i = 0; i < cantidad; i++) {
	            Asiento asiento = new Asiento();
	            asiento.setUnidad(unidad);
	            asiento.setCronograma(cronograma);
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

	    cronograma.setAsientos(asientos); 
		cronogramaDao.save(cronograma);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cronograma findOne(Integer id) {
		return cronogramaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		cronogramaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cronograma> findAll() {
		return (List<Cronograma>) cronogramaDao.findAll();
	}

	@Override
	public Cronograma save1(Cronograma cronograma) {
		return cronogramaDao.save(cronograma);
	}


	@Override
	@Transactional(readOnly=true)
	public Cronograma findById(Integer id) {
		return cronogramaDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete1(Integer id) {
		cronogramaDao.deleteById(id);
	}

	@Override
	public boolean existsByFechaAndUnidadAndHoraSalida(LocalDate fecha, Unidad unidad, String horaSalida) {
		 return cronogramaDao.existsByFechaAndUnidadAndHoraSalida(fecha, unidad, horaSalida);
	}

}
