package io.crew.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.crew.Certificate;
import io.crew.SkillType;

public class CertificateDao {
	
	/*
	public void create(Certificate cert) {
	    entityManager.persist(cert);
	    return;
	  }
	
	public Certificate getById(int id) {
		return entityManager.find(Certificate.class, id);
	}
	
	public void update(Certificate cert) {
		entityManager.merge(cert);
		return;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Certificate> findAll() {
	    return entityManager.createQuery("from certificates").getResultList();
	}
	
	public void delete(Certificate cert) {
	    if (entityManager.contains(cert))
	      entityManager.remove(cert);
	    else
	      entityManager.remove(entityManager.merge(cert));
	    return;
	  }
	
	@PersistenceContext
	private EntityManager entityManager;
	*/
}
