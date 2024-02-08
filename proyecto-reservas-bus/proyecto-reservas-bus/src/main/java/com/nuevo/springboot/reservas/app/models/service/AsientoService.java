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

	/* Funciones: Funciones CRUD*/
	/* Descripcion: Funciones de busqueda, guardar, editar y eliminar */
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

	/* Funciones: Cambiar estado*/
	/* Descripcion:Una vez deseleccionado se cambia el estado del asiento a disponible */
	@Override
	@Transactional
	public void updateEstadoToDisponible(Integer cronogramaId) {
		asientoDao.updateEstadoToDisponible(cronogramaId);
		
	}

	/* Funcion: Obtener el costo del cronograma */
	/* Descripcion: Dependiendo del cronograma se obtiene el costo almacenado en ruta */
	@Override
	public double obtenerCostoRutaPorCronogramaId(Integer cronogramaId) {
	    Double costo = asientoDao.obtenerCostoRutaPorCronogramaId(cronogramaId);
	    return redondearDosDecimales(costo);
	}
	
	
	/* Funcion: Obtener el costo total con cantidad de asientos */
	/* Descripcion: Se multiplica la cantidad de asientos selecccionados por el costo */
	@Override
	public double obtenerCostoTotal(Integer cronogramaId) {
	    List<Asiento> asientosReservados = asientoDao.countByEstado(cronogramaId);
	    int cantidadDeAsientosReservados = asientosReservados.size();
	    Double costoRuta = asientoDao.obtenerCostoRutaPorCronogramaId(cronogramaId);
	    return redondearDosDecimales(costoRuta * cantidadDeAsientosReservados);
	}
	
	
	/* Funcion: Obtener el costo total con descuento */
	/* Descripcion:Costo total aplicado el descuento de la configuracion activa */
	@Override
	public double obtenerCostoTotalD(Integer cronogramaId) {
		double iva = configuracionService.obtenerIVA();
		double total = (obtenerCostoTotal(cronogramaId)*iva);
		double cantidad = (obtenerCostoTotal(cronogramaId));
	    Double descuento = (configuracionService.obtenerDescuento())*(total+cantidad);
	    return redondearDosDecimales((total+cantidad)-descuento);
	}
	
	
	/* Funcion: Redondear a dos decimales */
	/* Descripcion:Obtener el costo a dos decimales (cualquier costo) */
	private double redondearDosDecimales(Double valor) {
	    if (valor == null) {
	        return 0.0;
	    }
	    BigDecimal bd = BigDecimal.valueOf(valor);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	
	
	/* Funcion: Subtotal sin iva */
	/* Descripcion:Obtener el costo total sin estar aplicado el iva */
	@Override
	public double obtenerSubtotal(Integer cronogramaId) {
		double iva = configuracionService.obtenerIVA();
		double total = (obtenerCostoTotal(cronogramaId)*iva);
		double cantidad = (obtenerCostoTotal(cronogramaId));
		return redondearDosDecimales(total+cantidad);
	}
	
	
	/* Funcion: Cantidad de asientos */
	/* Descripcion:Obtener cantidad de asientos que pueda comprar el cliente por unidad */
	@Override
	public int obtenerMax() {
		int cantidad= configuracionService.obtenerMax();
		
		return cantidad;
	}


	/* Funcion: Obtener costo con descuento aplicado */
	/* Descripcion:Obtener costo con el descuento aplicado sin iva */
	@Override
	public double obtenerAplicado(double costo) {
		Double descuento = (configuracionService.obtenerDescuento()*costo);
		return redondearDosDecimales(costo-descuento);
	}
	

	

	

}
