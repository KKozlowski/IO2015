package io.crew.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.PermissionType;
import io.crew.Employee;
import io.crew.EmployeeAssignment;
import io.general.App;

@Controller
public class CrewMasterController {
	@RequestMapping("/MasterPanel")
	@ResponseBody
	public String ManagerPanel(HttpSession h)
	{
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.crewMaster))
		{
			String result="<!DOCTYPE html><html><body>"
					+"<p>Wybierz zadanie</p>";
			List<EmployeeAssignment> assignments=App.getInstance().getCrew().returnEmployeeAssignments(App.getInstance().getUsers().getCurrentUser().getPersonalData().getPESEL());
			for(EmployeeAssignment assignment : assignments)
			{
				result+="<br />"+assignment;
			}
			result+="<form id='frm1' action='Assing'>"
					+ "<br>ID zadania: <input type='text' name='id'><br>"
					+ "Pracownik: <input type='text' name='pracownik'><br><br>"
					+ "<input type='button' onclick='myFunction()' value='Submit'></form>"
					+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>";
			result+="</body></html>";
			return result;
		}
		else
		{
			return null;
		}
	}
	@RequestMapping("/Assign")
	@ResponseBody
	public String Assign(HttpSession h, String id, String pracownik)
	{
		//TODO zapis do bazy itd.
		//Integer.parseInt(id);
		return "Przypisano pracownika "+pracownik+" do zadania: "+id;
	}
	@RequestMapping("/CreateCertificate")
	@ResponseBody
	public String CreateCertificatePanel(HttpSession h)
	{
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.crewMaster))
		{
			String result="<!DOCTYPE html><html><body>"
					+"<p>Stwórz nowy certyfikat</p>";
			result+="<form id='frm1' action='NewCert'>"
					+ "<br>ID certyfikatu: <input type='text' name='id'><br>"
					+ "Nazwa certyfikatu: <input type='text' name='nazwa'><br><br>"
					+ "<input type='button' onclick='myFunction()' value='Submit'></form>"
					+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>";
			result+="</body></html>";
			return result;
		}
		else
		{
			return null;
		}
	}

	@RequestMapping("/NewCert")
	@ResponseBody
	public String NewCert(HttpSession h, String id, String nazwa)
	{
		//TODO zapis do bazy itd.
		//Integer.parseInt(id);
		return "Utworzono certyfikat "+nazwa+" o ID: "+id;
	}
	@RequestMapping("/AddCertificate")
	@ResponseBody
	public String AddCertificatePanel(HttpSession h)
	{
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.crewMaster))
		{
			String result="<!DOCTYPE html><html><body>"
					+"<p>Przypisz certyfikat pracownikowi</p>";
			result+="Pracownicy:";
			List<Employee> employees = App.getInstance().getCrew().returnEmployees();
			for(Employee emlpoyee : employees)
			{
				result+="<br>"+emlpoyee.getPersonalData().getCardID()+" "+emlpoyee.getPersonalData().getName()+" "+emlpoyee.getPersonalData().getSurname();
			}
			result+="<form id='frm1' action='AddCert'>"
					+ "<br>Pracownik: <input type='text' name='pracownik'><br>"
					+ "Nazwa certyfikatu: <input type='text' name='nazwa'><br><br>"
					+ "<input type='button' onclick='myFunction()' value='Submit'></form>"
					+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>";
			result+="</body></html>";
			return result;
		}
		else
		{
			return null;
		}
	}

	@RequestMapping("/AddCert")
	@ResponseBody
	public String AddCert(HttpSession h, String pracownik, String nazwa)
	{
		//TODO zapis do bazy itd.
		//Integer.parseInt(id);
		return "Pracownikowi "+pracownik+" przypisano certyfikat "+nazwa;
	}
	@RequestMapping("/NewSkillType")
	@ResponseBody
	public String NewSkillTypePanel(HttpSession h)
	{
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.crewMaster))
		{
			String result="<!DOCTYPE html><html><body>"
					+"<p>Tworzenie umiejętności</p>";
			result+="<form id='frm1' action='AddSkill'>"
					+ "<br>ID umiejętności: <input type='text' name='id'><br>"
					+ "Nazwa umiejętności: <input type='text' name='skill'><br><br>"
					+ "<input type='button' onclick='myFunction()' value='Submit'></form>"
					+"<script> function myFunction() { document.getElementById('frm1').submit(); } </script>";
			result+="</body></html>";
			return result;
		}
		else
		{
			return null;
		}
	}
	@RequestMapping("/AddSkill")
	@ResponseBody
	public String AddSkill(HttpSession h, String id, String skill)
	{
		//TODO brakująca funkcja w staffdeployment
		//Integer.parseInt(id);
		return "Stworzono umiejętność "+skill+" o ID: "+id;
	}
	@RequestMapping("/AddTask")
	@ResponseBody
	public String AddTaskPanel(HttpSession h)
	{
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.crewMaster))
		{
				return "<!DOCTYPE html><html><body>"
						+ "<p>Utwórz zadanie</p>"
						  +"</body></html>";
		}
		else
		{
			return null;
		}
	}
	@RequestMapping("/NewTask")
	@ResponseBody
	public String NewTask(HttpSession h, String id, String skill)
	{
		//TODO brakująca funkcja w staffdeployment
		//Integer.parseInt(id);
		return "Zadanie";
	}
}
