package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Unidad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UnidadDaoImpl implements IConsolidatedDao<Unidad> {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Unidad>findAll() {
		
		return em.createQuery("from Unidad").getResultList();
	}

	@Override
	@Transactional
	public void save(Unidad unidad) {
		if(unidad.getId() != null && unidad.getId() > 0) {
			em.merge(unidad);
		}else {
		em.persist(unidad);
		}
	}
	

	@Override
	@Transactional
	public Unidad findOne(Integer id) {
			
		return em.find(Unidad.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(findOne(id));
	}

	
}
