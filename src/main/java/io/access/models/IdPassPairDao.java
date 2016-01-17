package io.access.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.access.IdPassPair;

@Repository
@Transactional
public class IdPassPairDao {
	
	//------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Save an IdPassPair to the database.
	 */
	public void create(IdPassPair ipp) {
	    entityManager.persist(ipp);
	    return;
	  }
	
	/**
	 * Delete an IdPassPair from the database.
	 */
	public void delete(IdPassPair ipp) {
	    if (entityManager.contains(ipp))
	      entityManager.remove(ipp);
	    else
	      entityManager.remove(entityManager.merge(ipp));
	    return;
	  }
	
	/**
	* Return the IdPassPair having the passed userID.
	*/
	public IdPassPair getById(int id) {
		return entityManager.find(IdPassPair.class, id);
	}
	
	/**
	* Update the passed IdPassPair in the database.
	*/
	public void update(IdPassPair ipp) {
		entityManager.merge(ipp);
		return;
	}
	
	// ------------------------
	// PRIVATE FIELDS
	// ------------------------
	
	// An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager entityManager;
}
