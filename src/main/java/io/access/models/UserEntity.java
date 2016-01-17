package io.access.models;

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
@Table(name = "users")
public class UserEntity {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected int id;
  
  @NotNull
  
  protected String nick;
  
  @NotNull
  protected boolean netUser;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public UserEntity() { }
  public UserEntity(int id)
  {
	  this.id = id;
  }

  public UserEntity(String nick, boolean net) {
    this.nick = nick;
    this.netUser = net;
  }

  public int getId() {
    return id;
  }

  /*public void setId(int value) {
    this.id = value;
  }*/
  
  public String getNick() {
    return nick;
  }
  
  public boolean isNetUser(){
	  return netUser;
  }

  /*public void setNick(String value) {
    this.nick = value;
  }*/
  
} // class Testuser
