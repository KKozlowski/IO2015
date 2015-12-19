package io.access.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.general.App;

@Controller
public class LoginController {

  @RequestMapping("/netLogin")
  @ResponseBody
  public String netLogin(HttpSession h) {
    return 
    		"<!DOCTYPE html><html><body><p>Logowanie klientów:</p>"
			+"<form id='frm1' action='netLoginSend'> Nick: <input type='text' name='nick'><br>Hasło: <input type='text' name='pass'><br><br><input type='button' onclick='myFunction()' value='Submit'></form>"
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
	    return App.getInstance().getUsers().netLogin(nick,pass).toString();
  }
  
  @RequestMapping("/innerLoginSend")
  @ResponseBody
  public String innerLoginSend(HttpSession h, String nick, String pass) {
	  return App.getInstance().getUsers().innerLogin(nick,pass).toString();
  }
 

}
