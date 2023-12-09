package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class RutaDaoImpl implements IConsolidatedDao<Ruta> {

	@PersistenceContext 
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Ruta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Ruta").getResultList();
	}

	@Override
	@Transactional
	public void save(Ruta ruta) {
		// TODO Auto-generated method stub
		if(ruta.getId() != null && ruta.getId() > 0) {
			em.merge(ruta);
		}else {
		em.persist(ruta);
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

	@Override
	@Transactional
	public Ruta findOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Ruta.class, id);
	}
	
	
}
