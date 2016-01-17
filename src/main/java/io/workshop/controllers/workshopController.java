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
		return "Dodawanie usług"
				+"<br><br>Nazwa: <input type='text' name='name'>"
				+"<br>W trakcie pracy: <input type='text' name='inProgress'>"
				+"<br>Dodatkowe informacje: <input type='text' name='additionalInfo'>"
				+"<br>Data Rozpoczoczecia: <input type='text' name='dateStarted'>"
				+"<br>Data Zakonczenia: <input type='text' name='dateEnded'><br>"
				
				+"<br><input type='button' value='Submit'></form>";
		return "";
		
	}
	
	@RequestMapping(value = "/removeServices")
	@ResponseBody
	public String removeServices(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "Usuwanie usługi"
				+"<br><br>Podaj id usługi: <input type='text' name='id'>"
		
				+"<br><input type='button' value='Submit'></form>";
		return "";
		
	}
	
	@RequestMapping(value = "/checkCurrentTasks")
	@ResponseBody
	public String checkCurrentTasks(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return  "Aktualne zadania:";

		return "";
		
	}
	
	@RequestMapping(value = "/checkAllTasks")
	@ResponseBody
	public String checkAllTasks(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "Wszystkie zadania:";

		return "";
		
	}
	
	@RequestMapping(value = "/employeeAssignment")
	@ResponseBody
	public String employeeAssignment(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "Przypisz pracownika do zadania"
				+"<br><br>NICK pracownika: <input type='text' name='name'>"
				+"<br>ID zadania <input type='text' name='id'>"
				
				+"<br><input type='button' value='Submit'></form>";
		return "";
		
	}
	
	@RequestMapping(value = "/itemAssignment")
	@ResponseBody
	public String itemAssignment(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "Dodaj przedmiot do zlecenia"
				+"<br><br>Nazwa przedmiu(unikalna): <input type='text' name='name'>"
				+"<br>W ID zadania <input type='text' name='id'>"
				
				+"<br><input type='button' value='Submit'></form>";
		return "";
		
	}
	
}
