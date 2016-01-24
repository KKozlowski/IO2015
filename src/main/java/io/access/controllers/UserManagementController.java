package io.access.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.InnerUser;
import io.access.PermissionType;
import io.access.Permissions;
import io.access.PersonalData;
import io.access.User;
import io.general.App;

@Controller
public class UserManagementController {

  @RequestMapping("/addEmployee")
  @ResponseBody
  public String addEmployee(HttpSession h) {
	if(App.getInstance().getUsers().isCurrentUserAdmin(h.getId())){
		return "<!DOCTYPE html><html><body><p>Dodawanie pracowników:</p>"
		  +"<form id='frm1' action='addEmployeeSend'>"
		  +"NICK: <input type='text' name='nick'><br>"
		  +"HASŁO: <input type='password' name='pass'><br>"
		  +"Imię: <input type='text' name='name'><br>"
		  +"Nazwisko: <input type='text' name='surname'><br>"
		  +"e-mail: <input type='email' name='email'><br>"
		  +"PESEL: <input type='text' name='PESEL'><br>"
		  +"Numer dowodu osobistego: <input type='text' name='idid'><br>"
		  +"Adres: <input type='text' name='adres'><br>"
		  
		  +"<br><input type='button' onclick='myFunction()' value='Submit'></form>"
		  
		  +"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
		  +"</body></html>";
	}
	else {
		  return "YOU DON'T HAVE MATCHING PERMISSIONS";
	}
  }
  @RequestMapping("/addEmployeeSend")
  @ResponseBody
  public String addEmployeeSend(HttpSession h, String nick, String pass, String name, String surname, String email, String idid, String PESEL, String adres) {
	if(App.getInstance().getUsers().isCurrentUserAdmin(h.getId())){
		InnerUser iu = App.getInstance().getUsers().registerEmployee(
				nick, 
				new PersonalData(name, surname, PESEL, adres, idid, email), 
				new Permissions(), 
				pass);
		if (iu != null)
			return "Dodano użytkownika "+ iu.getNick()+"<br><a href='/'>Wróć</a>";
		else
			return "Niepowodzenie dodawania użytkownika";
	}
	else {
		return "YOU DON'T HAVE MATCHING PERMISSIONS";
	}
  }
  
  @RequestMapping("/permissionEdit")
  @ResponseBody
  public String editPermissions(HttpSession h) {
	if(App.getInstance().getUsers().isCurrentUserAdmin(h.getId())){
		return "<!DOCTYPE html><html><body><p>Wpisz nick pracownika, któremu chcesz zmienić uprawnienia:</p>"
				  +"NICK: <input type='text' id='nick' name='nick'><br>"
				  
				  +"<br><input type='button' onclick='myFunction()' value='Submit'>"
				  
				  +"<script>"
				  +"function myFunction() { "
				  + "window.open('/permissionEditUser/'+ document.getElementById('nick').value, '_self'); "
				  + "} </script>"
				  +"</body></html>";
	}
	else {
		return "YOU DON'T HAVE MATCHING PERMISSIONS";
	}
  }
  
  @RequestMapping(value="/permissionEditUser/{nick}")
  @ResponseBody
  public String editPermissionsUser(HttpSession h, @PathVariable String nick) {
	System.out.println(nick);
	InnerUser iu = App.getInstance().getUsers().getInnerUserByNick(nick);
	System.out.println(iu);
	if (iu == null){
		return "NO SUCH EMPLOYEE";
	}
	if(App.getInstance().getUsers().isCurrentUserAdmin(h.getId())){
	  
		String result = "<!DOCTYPE html><html><body><p>Lista uprawnień uzytkownika :</p>"
				  +"<form id='frm1' action='permissionEditSend'>";
				  
				  
				  for (PermissionType p : PermissionType.values())
					  if (iu.getPermissions().contains(p))
						  result += p.toString() + ": <input type='checkbox' name='"+p.toString()+"' checked><br>";
					  else 
						  result += p.toString() + ": <input type='checkbox' name='"+p.toString()+"'><br>";
				  result +="<br><input type='button' onclick='myFunction()' value='Submit'>" 
				  +"</form>"
				  
				  +"<script> function myFunction() {"
				  +"var elements = document.getElementById('frm1').elements; var urrl = '/permissionEditSend/';"

				  +"for (var i = 0, element; element = elements[i++];) {"
				  +"    if (element.type === 'checkbox')"
				  +"        if (element.checked) urrl+='1,'; else urrl+='0,';"
				  +"}"
				  //+"document.getElementById('frm1').submit(); "
				  +"urrl+='&"+iu.getNick()+"';"
				  +"window.open(urrl,'_self');"
				  +"} </script><br><a href='/'>Wróć</a>"
				  +"</body></html>";
				  return result;
	}
	else {
		return "<a href='/'>Wróć</a>";
	}
  }
  
  @RequestMapping(value="/permissionEditSend/{perms}&{nick}")
  @ResponseBody
  public String editPermissionsSend(HttpSession h, @PathVariable String[] perms, @PathVariable String nick)
  {
	  if(App.getInstance().getUsers().isCurrentUserAdmin(h.getId())){
		  System.out.println(nick);
		  InnerUser iu = (InnerUser)App.getInstance().getUsers().getInnerUserByNick(nick);
		  int iterator = 0;	  
		  for (PermissionType p : PermissionType.values()){
			  if (perms[iterator].equals("1")){
				  iu.getPermissions().addPermission(p);
			  } else {
				  iu.getPermissions().removePermission(p);
			  }
			  iterator++;
		  }
		  App.getInstance().getUsers().logout(iu.getID());
	  }
	  return editPermissionsUser(h, nick);
  }
}
