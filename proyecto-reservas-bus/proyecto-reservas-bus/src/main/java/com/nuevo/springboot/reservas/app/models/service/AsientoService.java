package com.nuevo.springboot.reservas.app.models.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IAsientoDao;
import com.nuevo.springboot.reservas.app.models.entity.Asiento;


@Service
public class AsientoService implements IAsientoService{
	
	@Autowired
	private IAsientoDao asientoDao;
	
	@Autowired
	private IConfiguracionService configuracionService;

	@Override
	@Transactional
	public void save(Asiento asiento) {
		asientoDao.save(asiento);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Asiento findOne(Integer id) {
		return asientoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		asientoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Asiento> findAll() {
		return (List<Asiento>) asientoDao.findAll();
	}

	@Override
	public List<Asiento> findByCronogramaId(Integer cronogramaId) {
		return asientoDao.findByCronogramaId(cronogramaId);
	}

	@Override
	public List<Asiento> findByCronogramaId1(Integer cronogramaId) {
		return asientoDao.findByCronogramaId1(cronogramaId);
	}
	
	@Override
	public List<Asiento> findByCronogramaId2(Integer cronogramaId) {
		return asientoDao.findByCronogramaId2(cronogramaId);
	}
	
	@Override
	public List<Asiento> findByCronogramaId3(Integer cronogramaId) {
		return asientoDao.findByCronogramaId3(cronogramaId);
	}
	
	@Override
	public List<Asiento> findByCronogramaId4(Integer cronogramaId) {
		return asientoDao.findByCronogramaId4(cronogramaId);
	}

	@Override
	public Object[] obtenerCronogramaPorId(Integer idCronograma) {
		return asientoDao.obtenerCronogramaPorId(idCronograma);
	}

	@Override
	public List<Asiento> findByIds(List<Integer> idsAsientos) {
		// TODO Auto-generated method stub
		return asientoDao.findByIds(idsAsientos);
	}

	@Override
	public List<Asiento> countByEstado(Integer cronogramaId) {
		// TODO Auto-generated method stub
		return asientoDao.countByEstado(cronogramaId);
	}

	@Override
	@Transactional
	public void updateEstadoToDisponible(Integer cronogramaId) {
		asientoDao.updateEstadoToDisponible(cronogramaId);
		
	}

	@Override
	public double obtenerCostoRutaPorCronogramaId(Integer cronogramaId) {
	    Double costo = asientoDao.obtenerCostoRutaPorCronogramaId(cronogramaId);
	    return redondearDosDecimales(costo);
	}

	@Override
	public double obtenerCostoTotal(Integer cronogramaId) {
	    List<Asiento> asientosReservados = asientoDao.countByEstado(cronogramaId);
	    int cantidadDeAsientosReservados = asientosReservados.size();
	    Double costoRuta = asientoDao.obtenerCostoRutaPorCronogramaId(cronogramaId);
	    return redondearDosDecimales(costoRuta * cantidadDeAsientosReservados);
	}
	
	private double redondearDosDecimales(Double valor) {
	    if (valor == null) {
	        return 0.0;
	    }
	    BigDecimal bd = BigDecimal.valueOf(valor);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	@Override
	public double obtenerSubtotal(Integer cronogramaId) {
		double iva = configuracionService.obtenerIVA();
		double total = (obtenerCostoTotal(cronogramaId)*iva);
		double cantidad = (obtenerCostoTotal(cronogramaId));
		return redondearDosDecimales(total+cantidad);
	}
	
	
	@Override
	public int obtenerMax() {
		int cantidad= configuracionService.obtenerMax();
		
		return cantidad;
	}
	

	

	

}
