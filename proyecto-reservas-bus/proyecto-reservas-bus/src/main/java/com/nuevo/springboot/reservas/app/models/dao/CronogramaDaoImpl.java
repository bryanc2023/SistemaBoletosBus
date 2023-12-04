package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CronogramaDaoImpl implements ICronogramaDao {

	@PersistenceContext 
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cronograma> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cronograma").getResultList();
	}

	@Override
	@Transactional
	public void save(Cronograma cronograma) {
		// TODO Auto-generated method stub
		if(cronograma.getId() != null && cronograma.getId() > 0) {
			em.merge(cronograma);
		}else {
		em.persist(cronograma);
		}
	}

	@Override
	@Transactional
	public Cronograma findOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Cronograma.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

}
