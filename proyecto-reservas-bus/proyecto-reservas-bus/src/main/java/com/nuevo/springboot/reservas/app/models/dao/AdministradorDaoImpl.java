package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Administrador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class AdministradorDaoImpl implements IAdministradorDao {
	@PersistenceContext
	
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Administrador> findAll() {
		return em.createQuery("from Administrador").getResultList();
	}

	@Override
	@Transactional
	public void save(Administrador administrador) {
		if(administrador.getId() != null && administrador.getId() > 0) {
			em.merge(administrador);
		}else {
		em.persist(administrador);
		}
	}

	@Override
	@Transactional
	public Administrador findOne(Integer id) {
		return em.find(Administrador.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(findOne(id));
	}

}
