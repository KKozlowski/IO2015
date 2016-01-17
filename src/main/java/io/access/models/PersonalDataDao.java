package io.access.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonalDataDao {
	
	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Save PersonalData to the database.
	 */
	public void create(PersonalDataEntity personaldata) {
	    entityManager.persist(personaldata);
	    return;
	  }
	
	/**
	 * Delete PersonalData from the database.
	 */
	public void delete(PersonalDataEntity personaldata) {
	    if (entityManager.contains(personaldata))
	      entityManager.remove(personaldata);
	    else
	      entityManager.remove(entityManager.merge(personaldata));
	    return;
	  }
	
	/**
	* Return the data having the passed userID.
	*/
	public PersonalDataEntity getById(int userID) {
		System.out.println("PERSONALDATAENTITY GETBYID RAN x");
		if (entityManager.find(PersonalDataEntity.class, userID) != null)
			System.out.println("found");
		else
			System.out.println("not found");
		return entityManager.find(PersonalDataEntity.class, userID);
	}
	
	/**
	* Update the passed data in the database.
	*/
	public void update(PersonalDataEntity personaldata) {
		entityManager.merge(personaldata);
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
