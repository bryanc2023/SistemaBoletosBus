package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Personal;



import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PersonalDaoImpl implements IConsolidatedDao<Personal> {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Personal> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Personal").getResultList();
	}

	@Override
	@Transactional
	public void save(Personal personal) {
		// TODO Auto-generated method stub
		if(personal.getId() != null && personal.getId() > 0) {
			em.merge(personal);
		}else {
		em.persist(personal);
		}
	}

	@Override
	@Transactional
	public Personal findOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Personal.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

}
