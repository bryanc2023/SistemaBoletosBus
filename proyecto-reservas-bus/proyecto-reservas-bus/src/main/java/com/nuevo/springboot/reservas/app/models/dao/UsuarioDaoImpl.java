package com.nuevo.springboot.reservas.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.entity.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class UsuarioDaoImpl implements IConsolidatedDao<Usuario> {
	@PersistenceContext
	
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {
		return em.createQuery("from Usuario").getResultList();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		if(usuario.getId() != null && usuario.getId() > 0) {
			em.merge(usuario);
		}else {
		em.persist(usuario);
		}
	}

	@Override
	@Transactional
	public Usuario findOne(Integer id) {
		return em.find(Usuario.class, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(findOne(id));
	}

}
