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
			+"<form id='frm1' action='netLoginSend'> Nick: <input type='text' name='nick'><br>Hasło: <input type='password' name='pass'><br><br><input type='button' onclick='myFunction()' value='Submit'></form>"
			+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
			+"</body></html>";
  }
  
  @RequestMapping("/netRegister")
  @ResponseBody
  public String netRegister(HttpSession h) {
    return 
    		"<!DOCTYPE html><html><body><p>Rejestracja klientów:</p>"
			+"<form id='frm1' action='netRegisterSend'> Nick: <input type='text' name='nick'><br>"
    		+"Hasło: <input type='password' name='pass'><br>"
    		+"Imię: <input type='text' name='name'><br>"
    		+"Nazwisko: <input type='text' name='surname'><br>"
    		+"e-mail: <input type='email' name='email'><br>"
			+"<br><input type='button' onclick='myFunction()' value='Submit'></form>"
			+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
			+"</body></html>";
  }
  
  @RequestMapping("/innerLogin")
  @ResponseBody
  public String innerLogin(HttpSession h) {
	  return 
		"<!DOCTYPE html><html><body><p>Logowanie pracowników:</p>"
		+"<form id='frm1' action='innerLoginSend'> Nick: <input type='text' name='nick'><br>Hasło: <input type='password' name='pass'><br><br><input type='button' onclick='myFunction()' value='Submit'></form>"
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
  public String innerRegisterSend(HttpSession h, String nick, String pass, String name, String surname, String email) {
	  PersonalData pd = new PersonalData(name, surname, "", "", "", email);
	  User u = App.getInstance().getUsers().registerNetUser(nick, pd, pass);
	  if (u == null)
		  return "Niepowodzenie rejestracji. <a href='/netRegister'>Spróbuj jeszcze raz</a>";
	  else{
		  return u.getNick() + "<br /><a href='/netLogin'>Zaloguj się</a>";
	  }
  }
  
  @RequestMapping("/editPersonalData")
  @ResponseBody
  public String editPersonalData(HttpSession h) {
	  if (App.getInstance().getUsers().isUserLogged(h.getId())){
		  User u = App.getInstance().getUsers().getUserBySessionID(h.getId());
		  return "<!DOCTYPE html><html><body><p>Logowanie pracowników:</p>"
		  +"<form id='frm1' action='editPersonalDataSend'>"
    	  +"Imię: <input type='text' name='name' value='"+u.getPersonalData().getName()+"'><br>"
    	  +"Nazwisko: <input type='text' name='surname' value='"+u.getPersonalData().getSurname()+"'><br>"
    	  +"e-mail: <input type='email' name='email' value='"+u.getPersonalData().getMail()+"'><br>"
		  +"PESEL: <input type='text' name='PESEL' value='"+u.getPersonalData().getPESEL()+"'><br>"
    	  +"Numer dowodu osobistego: <input type='text' name='idid' value='"+u.getPersonalData().getCardID()+"'><br>"
		  +"Adres: <input type='text' name='adres' value='"+u.getPersonalData().getAdress()+"'><br>"
		  
		  +"<br><input type='button' onclick='myFunction()' value='Submit'></form>"
		  
		  +"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
		  +"</body></html>";
	  } else {
		  return "You are not logged in";
	  }
  }
  
  @RequestMapping("/editPersonalDataSend")
  @ResponseBody
  public String editPersonalDataSend(HttpSession h, String name, String surname, String email, String idid, String PESEL, String adres) {
	  System.out.println(PESEL);
	  User u = App.getInstance().getUsers().getUserBySessionID(h.getId());
	  if (u!=null){
		  u.getPersonalData().updataData(name, surname, email, idid, PESEL, adres);
	  }
		  
	  return editPersonalData(h);
  }
  
  @RequestMapping("/logout")
  @ResponseBody
  public String logout(HttpSession h) {
	  App.getInstance().getUsers().logout(h.getId());
	  return "Wylogowano pomyślnie.<br /><a href='/'>Wróć</a>";
  }

}
