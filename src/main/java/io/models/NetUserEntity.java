package io.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an Testuser for this web application.
 */
@Entity
@Table(name = "netUsers")
public class NetUserEntity {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected long id;
  
  @NotNull
  protected String nick;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public NetUserEntity() { }

  public NetUserEntity(long id) { 
    this.id = id;
  }

  public NetUserEntity(String nick) {
    this.nick = nick;
  }

  public long getId() {
    return id;
  }

  /*public void setId(long value) {
    this.id = value;
  }*/
  
  public String getNick() {
    return nick;
  }

  /*public void setNick(String value) {
    this.nick = value;
  }*/
  
} // class Testuser
