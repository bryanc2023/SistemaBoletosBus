package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DetalleDaoImpl implements IConsolidatedDao<Detalle> {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true) 
	@Override
	public List<Detalle> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Detalle").getResultList();
	}

	@Override
	@Transactional
	public void save(Detalle detalle) {
		if(detalle.getId() != null && detalle.getId() > 0) {
			em.merge(detalle);
		}else {
		em.persist(detalle);
		}

	}

	@Override
	@Transactional
	public Detalle findOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Detalle.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(findOne(id));

	}

}
