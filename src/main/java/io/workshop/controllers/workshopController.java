package io.workshop.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.InnerUser;
import io.access.PermissionType;
import io.access.Permissions;
import io.access.PersonalData;
import io.general.App;
import io.workshop.FixCommision;
import io.workshop.FixCommisionDao;



@Controller
public class workshopController {
	@Autowired
	private FixCommisionDao repository;
	
	@RequestMapping(value = "/workshop")
	 @ResponseBody
	public String workshop(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
		
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
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
		return "<!DOCTYPE html><html><body><p>Dodawanie usługi:</p>"
				+"<form id='frm1' action='/addCommisionSend' method='POST'>"
				+"<br /><br>Nazwa: <input type='text' name='name'/>"
				+"<br />W trakcie pracy: <input type='text' name='inProgress'/>"
				+"<br />Dodatkowe informacje: <input type='text' name='additionalInfo'/>"
				+"<br />Data Rozpoczoczecia: <input type='text' name='dateStarted'/>"
				+"<br />Data Zakonczenia: <input type='text' name='dateEnded'/><br />"

				+"<br /><button type='submit'>Submit</button></form>"
				+"</body></html>";
		else
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		
	}
	
	@RequestMapping("/addCommisionSend")
	  @ResponseBody
	  public String addCommisionSend(HttpSession h, String name, String inProgress, String additionalInfo, String dateStarted, String dateEnded) throws ParseException {
		System.out.println("asd");
		if(App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan)){
			
					FixCommision fixCommision = new FixCommision();
					fixCommision.setName(name);
					fixCommision.setInProgress(Boolean.valueOf(inProgress));
					fixCommision.setAdditionalInfo(additionalInfo);
					DateFormat format = new SimpleDateFormat("dd.MM.yy", Locale.ENGLISH);
					Date dateS = format.parse(dateStarted);
					fixCommision.setDateStarted(dateS);
					Date dateE = format.parse(dateEnded);
					fixCommision.setDateEnded(dateE);
					repository.create(fixCommision);
					System.out.println("dodawanie serwisu zakonczone sukcesem");
					
					return "Dodowanie zakończone sukcesem";
		}
		else 
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		
	  }
	
	@RequestMapping(value = "/removeServices")
	@ResponseBody
	public String removeServices(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
		return "Usuwanie usługi"
				+"<br><br>Podaj id usługi: <input type='text' name='id'>"
		
				+"<br><input type='button' value='Submit'></form>";
		return "";
		
	}
	
	@RequestMapping(value = "/checkCurrentTasks")
	@ResponseBody
	public String checkCurrentTasks(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
		return  "Aktualne zadania:";

		return "";
		
	}
	
	@RequestMapping(value = "/checkAllTasks")
	@ResponseBody
	public String checkAllTasks(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
		return "Wszystkie zadania:";

		return "";
		
	}
	
	@RequestMapping(value = "/employeeAssignment")
	@ResponseBody
	public String employeeAssignment(HttpSession h){
		
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
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
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan))
		return "Dodaj przedmiot do zlecenia"
				+"<br><br>Nazwa przedmiu(unikalna): <input type='text' name='name'>"
				+"<br>W ID zadania <input type='text' name='id'>"
				
				+"<br><input type='button' value='Submit'></form>";
		return "";
		
	}
	
}
