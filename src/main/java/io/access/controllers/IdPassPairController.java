package io.access.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.IdPassPair;
import io.general.App;
import io.models.IdPassPairDao;

@Controller
public class IdPassPairController {
// ------------------------
  // PUBLIC METHODS
  // ------------------------

  public IdPassPair create(IdPassPair ipp) {
    try {
      idPassPairDao.create(ipp);
    }
    catch (Exception ex) {
      return null;
    }
    return ipp;
  }
  
  public boolean delete(IdPassPair ipp) {
    try {
    	idPassPairDao.delete(ipp);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  }
  
  @RequestMapping(value="/getipp-by-id")
  @ResponseBody
  public IdPassPair getById(int id) {
	  IdPassPair pe;
    try {
    	pe = idPassPairDao.getById(id);
    }
    catch (Exception ex) {
      return null;
    }
    return pe;
  }
 
  public boolean update(IdPassPair ipp) {
    try {
    	idPassPairDao.update(ipp);
    }
    catch (Exception ex) {
      return false;
    }
    return true;
  } 
  
  public IdPassPairController() {
	App.getInstance().getUsers().setIdPassPairController(this);
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the personalDao used inside this controller.
  @Autowired
  public IdPassPairDao idPassPairDao;
}
