package io.storage.controllers;


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
import io.storage.ItemType;
import io.storage.SingleItem;

@Controller
public class AddItemController {
	
  @RequestMapping("/addItem")
  @ResponseBody
  public String addItemtoStorage(HttpSession h) {
	if(App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())){
		return "<!DOCTYPE html><html><body><p>Dodanie przedmiotu:</p>"
		  +"<form id='frm1' action='addItemSend'>"
		  +"Nazwa: <input type='text' name='name'><br>"
		  +"Opis: <input type='text' name='description'><br>"
		  
		  +"<br><input type='button' onclick='myFunction()' value='Submit'></form>"
		  
		  +"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
		  +"</body></html>";
	}
	else {
		  return "YOU DON'T HAVE MATCHING PERMISSIONS";
	}
  }
  @RequestMapping("/addItemSend")
  @ResponseBody
  public String addItemSend(HttpSession h, String name, boolean available, String description, ItemType itemtype) {
	if(App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())){
		SingleItem newitem = new SingleItem (name, true, description, ItemType.tool);
		App.getInstance().getStorage().addObject(newitem);
		if (newitem != null)
			return "Dodano przedmiot "+ newitem.getName()+"<br><a href='/'>Wróæ</a>";
		else
			return "Niepowodzenie dodawania przedmiotu";
	}
	else {
		return "YOU DON'T HAVE MATCHING PERMISSIONS";
	}
  }
  
  @RequestMapping("/ShowStorage")
  @ResponseBody
  public String ShowStorage(HttpSession h) {
	if(App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())){
		return App.getInstance().getStorage().toString();
	}
	else {
		return "YOU DON'T HAVE MATCHING PERMISSIONS";
	}
  }
  
}
