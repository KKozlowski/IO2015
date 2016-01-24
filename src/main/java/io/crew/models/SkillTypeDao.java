package io.crew.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.crew.SkillType;


public class SkillTypeDao {
	/*
	public void create(SkillType skill) {
	    entityManager.persist(skill);
	    return;
	  }
	
	public SkillType getById(int id) {
		return entityManager.find(SkillType.class, id);
	}
	
	public void update(SkillType skill) {
		entityManager.merge(skill);
		return;
	}	
	
	@SuppressWarnings("unchecked")
	public List<SkillType> findAll() {
	    return entityManager.createQuery("from skills").getResultList();
	}
	
	public void delete(SkillType skill) {
	    if (entityManager.contains(skill))
	      entityManager.remove(skill);
	    else
	      entityManager.remove(entityManager.merge(skill));
	    return;
	  }
	
	@PersistenceContext
	private EntityManager entityManager;
	*/
}
