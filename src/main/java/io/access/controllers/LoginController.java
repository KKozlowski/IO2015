package io.access.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.PersonalData;
import io.access.User;
import io.general.App;

@Controller
public class LoginController {

  @RequestMapping("/netLogin")
  @ResponseBody
  public String netLogin(HttpSession h) {
    return 
    		"<!DOCTYPE html><html><body><p>Logowanie klientów:</p>"
			+"<form id='frm1' action='netLoginSend'> Nick: <input type='text' name='nick' value='NICK'><br>Hasło: <input type='text' name='pass' value='PASSWORD'><br><br><input type='button' onclick='myFunction()' value='Submit'></form>"
			+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
			+"</body></html>";
  }
  
  @RequestMapping("/netRegister")
  @ResponseBody
  public String netRegister(HttpSession h) {
    return 
    		"<!DOCTYPE html><html><body><p>Rejestracja klientów:</p>"
			+"<form id='frm1' action='netRegisterSend'> Nick: <input type='text' name='nick'><br>"
    		+"Hasło: <input type='text' name='pass'><br>"
    		+"Imię: <input type='text' name='name'><br>"
    		+"Nazwisko: <input type='text' name='surname'><br>"
			+"<br><input type='button' onclick='myFunction()' value='Submit'></form>"
			+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
			+"</body></html>";
  }
  
  @RequestMapping("/innerLogin")
  @ResponseBody
  public String innerLogin(HttpSession h) {
	  return 
		"<!DOCTYPE html><html><body><p>Logowanie pracowników:</p>"
		+"<form id='frm1' action='innerLoginSend'> Nick: <input type='text' name='nick'><br>Hasło: <input type='text' name='pass'><br><br><input type='button' onclick='myFunction()' value='Submit'></form>"
		+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
		+"</body></html>";
  }
  
  @RequestMapping("/netLoginSend")
  @ResponseBody
  public String netLoginSend(HttpSession h, String nick, String pass) {
	    return App.getInstance().getUsers().netLogin(h.getId(), nick,pass).toString() + "<br /><a href='/'>Wróć</a>";
  }
  
  @RequestMapping("/innerLoginSend")
  @ResponseBody
  public String innerLoginSend(HttpSession h, String nick, String pass) {
	  return App.getInstance().getUsers().innerLogin(h.getId(), nick,pass).toString() + "<br /><a href='/'>Wróć</a>";
  }
 
  @RequestMapping("/netRegisterSend")
  @ResponseBody
  public String innerRegisterSend(HttpSession h, String nick, String pass, String name, String surname) {
	  PersonalData pd = new PersonalData(name, surname, null, null, null, null);
	  User u = App.getInstance().getUsers().registerNetUser(nick, pd, pass);
	  if (u == null)
		  return "Niepowodzenie rejestracji. <a href='/netRegister'>Spróbuj jeszcze raz</a>";
	  else{
		  return u.getNick() + "<br /><a href='/netLogin'>Zaloguj się</a>";
	  }
  }
  
  @RequestMapping("/logout")
  @ResponseBody
  public String logout(HttpSession h) {
	  App.getInstance().getUsers().logout(h.getId());
	  return "Wylogowano pomyślnie.<br /><a href='/'>Wróć</a>";
  }

}
