package io.storage.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StorageDao {

	//------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Save an new item to the database.
	 */
	public void create(StorageManagement sm) {
		entityManager.persist(sm);
		return;
	}

	/**
	 * Delete StorageManagement from the database.
	 */
	public void delete(StorageManagement sm) {
		if (entityManager.contains(sm)) {
			entityManager.remove(sm);
		} else {
			entityManager.remove(entityManager.merge(sm));
		}
		return;
	}

	/**
	 * Return the StorageManagement having the passed userID.
	 */
	public StorageManagement getById(int id) {
		return entityManager.find(StorageManagement.class, id);
	}

	/**
	 * Update the passed StorageManagement in the database.
	 */
	public void update(StorageManagement sm) {
		entityManager.merge(sm);
		return;
	}

	public List<StorageManagement> findAll() {
		return entityManager.createQuery(
				"FROM StorageManagement").getResultList();
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	// An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager entityManager;
}