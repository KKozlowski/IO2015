package io.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.PermissionType;
import io.general.App;

@Controller
public class MainController {

	@RequestMapping("/")
	@ResponseBody
	public String index(HttpSession h) {
		if (!App.getInstance().getUsers().isUserLogged(h.getId())) {
			String result = "SESSION ID: " + h.getId() + "<br />" +
					"<a href='/netLogin'>Zaloguj się jako klient</a> - <a href='/innerLogin'>Zaloguj się jako pracownik</a><br />" +
					"<a href='/netRegister'>Zarejestruj się</a>" +
					"<br /><a href='/about'>ABOUT</a>";

			return result;
		} else {
			String result = "SESSION ID: " + h.getId() + "<br />" +
					"<a href='/logout'>Wyloguj się</a>  -  " +
					"<a href='/editPersonalData'>Edytuj swoje dane</a>  -  " +
					"<a href='/changePass'>Zmien haslo</a>" +
					"<br /><a href='/about'>ABOUT</a>";
			if (App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.admin)
					&& App.getInstance().getUsers().isCurrentUserAdmin(h.getId())) {
				result += "<br>Witaj Lordzie Administratorze! Chwała wielkiej administraturze!<br>Oto twoje specjalne uprawnienia:<br>"
						+ "<a href='/addEmployee'>Dodaj pracownika</a><br>"
						+ "<a href='/permissionEdit'>Edytuj uprawnienia dostępowe pracowników</a>";
			}
			if (App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.workshopMan)) {
				result += "<br>Witaj Lordzie Administratorze! Chwała wielkiej administraturze!<br>Oto twoje specjalne uprawnienia:<br>"
						+ "<br><a href='/workshop'>Przejdź do serwisu</a><br>";
			}
			if (App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.storageWorker)) {
				result += "<br><br><a href='/storage'>Zarzadzaj magazynem</a>";
			}

			if (App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.netUser)) {
				result += "<br><br><a href='/storage'>Kliknij aby dokonac rezerwacji</a>";
			}
			return result;
		}
	}

	@RequestMapping("/about")
	@ResponseBody
	public String about() {
		return "First try.";
	}
}