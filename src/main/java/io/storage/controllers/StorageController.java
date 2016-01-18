package io.storage.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.PermissionType;
import io.general.App;

@Controller
public class StorageController {
	@RequestMapping(value = "/storage")
	@ResponseBody
	public String storage(HttpSession h) {
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId())
				|| App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.storageWorker)) {
			return "Witaj w magazynie"
					+ "<br><br><a href='/addItem'>Dodaj sprzet</a>"
					+ "<br><a href='/removeItem'>Usun sprzet</a>"
					+ "<br><a href='/ShowStorage'>Wyswietl caly sprzet</a>";
		} else if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId())
				|| App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.netUser)) {
			return "Witaj w Akwilonie"
					+ "<br><br><a href='/reserveBoat'>Zarezerwuj statek</a>"
					+ "<br><a href='/reserveMooringPlace'>Zarezerwuj cume</a>";
		}

		else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}

	}

}
