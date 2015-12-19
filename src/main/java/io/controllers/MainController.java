package io.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.general.App;

@Controller
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  public String index(HttpSession h) {
	  if (!App.getInstance().getUsers().isUserLogged(h.getId()))
		  return "SESSION ID: " + h.getId() + "<br />"+
    		"<a href='/netLogin'>Zaloguj się jako klient</a> - <a href='/innerLogin'>Zaloguj się jako pracownik</a><br />" +
    		"<a href='/netRegister'>Zarejestruj się</a>" +
    		"<br /><a href='/about'>ABOUT</a>";
	  else 
		  return "SESSION ID: " + h.getId() + "<br />"+
	  		"<a href='/logout'>Wyloguj się</a>" +
	  		"<br /><a href='/about'>ABOUT</a>";
  }
  
  @RequestMapping("/about")
  @ResponseBody
  public String about() {
    return "First try.";
  }

}
