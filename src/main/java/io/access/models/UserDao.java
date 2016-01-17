package io.access.models;

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
public class UserDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(UserEntity user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(UserEntity user) {
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
  public List<UserEntity> getAll() {
    return entityManager.createQuery("from Testuser").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  public UserEntity getByNick(String nick) {
    return (UserEntity) entityManager.createQuery(
        "from UserEntity where nick = :nick")
        .setParameter("nick", nick)
        .getSingleResult();
//	  return entityManager.find(UserEntity.class, nick);
  }
  
  public int getMaxID (){
	  org.hibernate.Session s = (org.hibernate.Session) (entityManager.unwrap(Session.class));
	  Criteria c = s.createCriteria(UserEntity.class).setProjection(Projections.max("id"));
	  int max = (int)c.uniqueResult();
	  return max;
  }

  /**
   * Return the user having the passed id.
   */
  public UserEntity getById(int id) {
    return entityManager.find(UserEntity.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(UserEntity user) {
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
