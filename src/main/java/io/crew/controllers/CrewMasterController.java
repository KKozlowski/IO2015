package io.crew.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.access.PermissionType;
import io.crew.EmployeeAssignment;
import io.general.App;

@Controller
public class CrewMasterController {
	@RequestMapping("/MasterPanel")
	@ResponseBody
	public String ManagerPanel(HttpSession h)
	{
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
				return "<!DOCTYPE html><html><body>"
						+ "<p>Wybierz zadanie</p>"
						  +"<select items='${assingments}'>"
						  + "<option>lololo</option>"
						  + "<option>hehehe</option>"
						  + "</select>"
					//	  +"<script>"
					//	  +"function myFunction() { "
					//	  + ""
					//	  + "}"
					//	  + "</script>"
						  +"</body></html>";
		}
		else
		{
			return null;
		}
	}
}
