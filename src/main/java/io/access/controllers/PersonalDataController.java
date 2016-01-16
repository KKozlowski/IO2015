package io.access.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.general.App;
import io.models.PersonalDataDao;
import io.models.PersonalDataEntity;

@Controller
public class PersonalDataController {
	
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  public PersonalDataEntity create(PersonalDataEntity pd) {
    try {
      personalDao.create(pd);
    }
    catch (Exception ex) {
      return null;
    }
    return pd;
  }
  
  public boolean delete(PersonalDataEntity pd) {
    try {
      personalDao.delete(pd);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  }
  
  @RequestMapping(value="/getpd-by-id")
  @ResponseBody
  public PersonalDataEntity getById(int id) {
	  PersonalDataEntity pd;
    try {
    	pd = personalDao.getById(id);
    }
    catch (Exception ex) {
      return null;
    }
    return pd;
  }
 
  public boolean update(PersonalDataEntity pd) {
    try {
      personalDao.update(pd);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  } 
  
  public PersonalDataController() {
	App.getInstance().getUsers().setPersonalDataController(this);
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the personalDao used inside this controller.
  @Autowired
  public PersonalDataDao personalDao;
}
