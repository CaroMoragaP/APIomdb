package com.apiomdb.demo.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apiomdb.demo.models.entity.Actividad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ActividadDao implements IActividadDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public List<Actividad> findAll() {
		return em.createQuery("select c from Actividad c").getResultList();
	}

	@Override
	@Transactional
	public void save(Actividad act) {
		if (act.getId() != null) {
			em.merge(act);
		} else {
			em.persist(act);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Actividad findOne(int id) {
		return em.find(Actividad.class, id);
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		em.remove(findOne(id));
	}
	
}

