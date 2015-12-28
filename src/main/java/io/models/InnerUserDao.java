package io.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.websocket.Session;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the Testuser entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class InnerUserDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(InnerUserEntity user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(InnerUserEntity user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<InnerUserEntity> getAll() {
    return entityManager.createQuery("from InnerUserEntity").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  public InnerUserEntity getByNick(String nick) {
    return (InnerUserEntity) entityManager.createQuery(
        "from InnerUserEntity where nick = :nick")
        .setParameter("nick", nick)
        .getSingleResult();
  }
  
  public int getMaxID (){
	  org.hibernate.Session s = (org.hibernate.Session) (entityManager.unwrap(Session.class));
	  Criteria c = s.createCriteria(InnerUserEntity.class).setProjection(Projections.max("id"));
	  int max = (int)c.uniqueResult();
	  return max;
  }

  /**
   * Return the user having the passed id.
   */
  public InnerUserEntity getById(long id) {
    return entityManager.find(InnerUserEntity.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(InnerUserEntity user) {
    entityManager.merge(user);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class TestuserDao
