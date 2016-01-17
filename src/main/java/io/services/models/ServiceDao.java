package io.services.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.services.Service;

@Repository
@Transactional
public class ServiceDao {
	
	public void create(Service service) {
	    entityManager.persist(service);
	    return;
	  }
	
	public void delete(Service service) {
	    if (entityManager.contains(service))
	      entityManager.remove(service);
	    else
	      entityManager.remove(entityManager.merge(service));
	    return;
	  }
	
	public Service getById(int id) {
		return entityManager.find(Service.class, id);
	}
	
	public void update(Service service) {
		entityManager.merge(service);
		return;
	}	

	public List<Service> findAll() {
	    return entityManager.createQuery(
	            "SELECT s FROM services s").getResultList();
	}
	
	@PersistenceContext
	private EntityManager entityManager;
}
