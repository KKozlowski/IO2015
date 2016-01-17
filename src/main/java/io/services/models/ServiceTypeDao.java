package io.services.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.services.Service;
import io.services.ServiceType;

@Repository
@Transactional
public class ServiceTypeDao {
	
	public void create(ServiceType serviceType) {
	    entityManager.persist(serviceType);
	    return;
	  }
	
	public void delete(ServiceType serviceType) {
	    if (entityManager.contains(serviceType))
	      entityManager.remove(serviceType);
	    else
	      entityManager.remove(entityManager.merge(serviceType));
	    return;
	  }
	
	public ServiceType getById(int id) {
		return entityManager.find(ServiceType.class, id);
	}
	
	public void update(ServiceType serviceType) {
		entityManager.merge(serviceType);
		return;
	}	

	public List<Service> findAll() {
	    return entityManager.createQuery("from ServiceType").getResultList();
	}
	
	@PersistenceContext
	private EntityManager entityManager;
}
