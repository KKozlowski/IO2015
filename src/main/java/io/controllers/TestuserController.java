package io.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.models.Testuser;
import io.models.TestuserDao;

/**
 * Class TestuserController
 */
@Controller
public class TestuserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/create")
  @ResponseBody
  public Testuser create(String email, String name) {
	  Testuser user = null;
    try {
      user = new Testuser(email, name);
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
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      Testuser user = new Testuser(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "Testuser succesfully deleted!";
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      Testuser user = userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "Testuser not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      Testuser user = userDao.getById(id);
      user.setEmail(email);
      user.setName(name);
      userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "Testuser succesfully updated!";
  } 

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the TestuserDao used inside this controller.
  @Autowired
  private TestuserDao userDao;
  
} // class TestuserController
