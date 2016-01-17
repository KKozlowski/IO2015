package io.crew.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.crew.Employee;
import io.crew.EmployeeAssignment;
import io.services.ServiceType;

@Repository
@Transactional
public class EmployeeAssignmentDao {
	
	public void create(EmployeeAssignment assignment) {
	    entityManager.persist(assignment);
	    return;
	  }
	
	public EmployeeAssignment getById(int id) {
		return entityManager.find(EmployeeAssignment.class, id);
	}
	
	public void update(EmployeeAssignment assignment) {
		entityManager.merge(assignment);
		return;
	}	
	
	@SuppressWarnings("unchecked")
	public List<EmployeeAssignment> findAll() {
	    return entityManager.createQuery("from assignments").getResultList();
	}
	
	public void delete(EmployeeAssignment assignment) {
	    if (entityManager.contains(assignment))
	      entityManager.remove(assignment);
	    else
	      entityManager.remove(entityManager.merge(assignment));
	    return;
	  }
	
	@PersistenceContext
	private EntityManager entityManager;

}
