package io.workshop.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.PermissionType;
import io.general.App;


@Controller
public class workshopController {
	@RequestMapping(value = "/workshop")
	 @ResponseBody
	public String workshop(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		
			return "Witaj w serwisie"
					+"<br><br><a href='/addCommision'>Dodaj usługę</a>"
					+"<br><a href='/removeServices'>Usuń usługę</a>"
					+"<br><a href='/checkCurrentTasks'>Zobacz aktualne zadania</a>"
					+"<br><a href='/checkAllTasks'>Zobacz wszystkie zadania</a>"
					+"<br><a href='/employeeAssignment'>Przypisz pracownika do zadania</a>"
					+"<br><a href='/itemAssignment'>Dodaj przedmiot do serwisu</a>";

		
		else 
			 return "YOU DON'T HAVE MATCHING PERMISSIONS";
	
	}
	
	@RequestMapping(value = "/addCommision")
	@ResponseBody
	public String addCommison(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return null;
		return "";
		
	}
	
}
