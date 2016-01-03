package io.access.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.general.App;
import io.models.Testuser;
import io.models.TestuserDao;
import io.models.UserDao;
import io.models.UserEntity;

/**
 * Class TestuserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  public UserEntity create(UserEntity user) {
    try {
      userDao.create(user);
    }
    catch (Exception ex) {
      return null;
    }
    return user;
  }
  
  /**
   * Delete the user with the passed id.
   */
  public boolean delete(UserEntity user) {
    try {
      userDao.delete(user);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  }
  
  @RequestMapping(value="/getu-by-id")
  @ResponseBody
  public UserEntity getById(int id) {
	  UserEntity user;
    try {
    	user = userDao.getById(id);
    }
    catch (Exception ex) {
      return null;
    }
    return user;
  }
  
  @RequestMapping(value="/getu-by-nick")
  @ResponseBody
  public UserEntity getByNick(String nick) {
	  UserEntity user;
    try {
    	user = userDao.getByNick(nick);
    }
    catch (Exception ex) {
      return null;
    }
    return user;
  }
 
  public boolean update(UserEntity user) {
    try {
      userDao.update(user);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  } 
  
  public UserController() {
	App.getInstance().getUsers().setUserController(this);
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the TestuserDao used inside this controller.
  @Autowired
  public UserDao userDao;
  
} // class TestuserController
