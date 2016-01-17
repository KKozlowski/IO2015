package io.workshop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.workshop.FixCommision;

@Repository
@Transactional
public class FixCommisionDao {
	
	//------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Save an FixCommision to the database.
	 */
	public void create(FixCommision fc) {
	    entityManager.persist(fc);
	    return;
	  }
	
	/**
	 * Delete FixCommision from the database.
	 */
	public void delete(FixCommision fc) {
	    if (entityManager.contains(fc))
	      entityManager.remove(fc);
	    else
	      entityManager.remove(entityManager.merge(fc));
	    return;
	  }
	
	/**
	* Return the FixCommision having the passed userID.
	*/
	public FixCommision getById(int id) {
		return entityManager.find(FixCommision.class, id);
	}
	
	/**
	* Update the passed FixCommision in the database.
	*/
	public void update(FixCommision fc) {
		entityManager.merge(fc);
		return;
	}
	
	public List<FixCommision> findAll() {
	    return entityManager.createQuery(
	            "FROM FixCommision").getResultList();
	}
	
	public List<FixCommision> findTrue() {
	    return entityManager.createQuery(
	    		"FROM FixCommision WHERE inProgress = TRUE").getResultList();
	}
	
	// ------------------------
	// PRIVATE FIELDS
	// ------------------------
	
	// An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager entityManager;
}