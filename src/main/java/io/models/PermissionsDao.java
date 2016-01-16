package io.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PermissionsDao {
	
	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Save Permissions to the database.
	 */
	public void create(PermissionsEntity permissions) {
	    entityManager.persist(permissions);
	    return;
	  }
	
	/**
	 * Delete Permissions from the database.
	 */
	public void delete(PermissionsEntity permissions) {
	    if (entityManager.contains(permissions))
	      entityManager.remove(permissions);
	    else
	      entityManager.remove(entityManager.merge(permissions));
	    return;
	  }
	
	/**
	* Return the permissions having the passed userID.
	*/
	public PermissionsEntity getById(int userID) {
		return entityManager.find(PermissionsEntity.class, userID);
	}
	
	/**
	* Update the passed permissions in the database.
	*/
	public void update(PermissionsEntity permissions) {
		entityManager.merge(permissions);
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
