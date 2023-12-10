package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nuevo.springboot.reservas.app.models.entity.Pasajero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class PasajeroDaoImpl implements IConsolidatedDao<Pasajero> {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Pasajero> findAll() {
		return em.createQuery("from Pasajero").getResultList();
	}

	@Override
	@Transactional
	public void save(Pasajero pasajero) {
			if(pasajero.getId() !=null && pasajero.getId() >0) {
				em.merge(pasajero);
			}else {
				em.persist(pasajero);
			}
		
	}

	@Override
	@Transactional
	public Pasajero findOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Pasajero.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(findOne(id));
		
	}

	

}
