package io.access.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.models.PermissionsDao;
import io.access.models.PermissionsEntity;
import io.general.App;

@Controller
public class PermissionsController {
	
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  public PermissionsEntity create(PermissionsEntity pe) {
    try {
      permissionsDao.create(pe);
    }
    catch (Exception ex) {
      return null;
    }
    return pe;
  }
  
  public void setUserID(PermissionsEntity pe, int id) {
	  pe.setUserID(id);
  }
  
  public boolean delete(PermissionsEntity pe) {
    try {
      permissionsDao.delete(pe);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  }
  
  @RequestMapping(value="/getp-by-id")
  @ResponseBody
  public PermissionsEntity getById(int id) {
	  PermissionsEntity pe;
    try {
    	pe = permissionsDao.getById(id);
    }
    catch (Exception ex) {
      return null;
    }
    return pe;
  }
 
  public boolean update(PermissionsEntity pe) {
    try {
      permissionsDao.update(pe);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  } 
  
  public PermissionsController() {
	App.getInstance().getUsers().setPermissionsController(this);
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the personalDao used inside this controller.
  @Autowired
  public PermissionsDao permissionsDao;
}
