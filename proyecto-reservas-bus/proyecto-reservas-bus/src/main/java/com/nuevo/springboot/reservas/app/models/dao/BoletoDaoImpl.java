package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BoletoDaoImpl implements IConsolidatedDao<Boleto> {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Boleto> findAll() {
		
		return em.createQuery("from Boleto").getResultList();
	}

	@Override
	@Transactional
	public void save(Boleto boleto) {
		if(boleto.getId() != null && boleto.getId() > 0) {
			em.merge(boleto);
		}else {
		em.persist(boleto);
		}
	}

	@Override
	public Boleto findOne(Integer id) {
			
		return em.find(Boleto.class, id);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(findOne(id));
	}
}

