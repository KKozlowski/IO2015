package io.crew.models;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.crew.Employee;
import io.services.Service;
import io.services.ServiceType;

@Repository
@Transactional
public class EmployeeDao {
	
	/*
	public void create(Employee employee) {
	    entityManager.persist(employee);
	    return;
	  }
	
	public Employee getById(int id) {
		return entityManager.find(Employee.class, id);
	}
	
	public void update(Employee employee) {
		entityManager.merge(employee);
		return;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
	    return entityManager.createQuery("from Employee").getResultList();
	}
	
	public void delete(Employee employee) {
	    if (entityManager.contains(employee))
	      entityManager.remove(employee);
	    else
	      entityManager.remove(entityManager.merge(employee));
	    return;
	  }
	
	@PersistenceContext
	private EntityManager entityManager;
	*/
}


