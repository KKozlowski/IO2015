package io.storage.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.general.App;
import io.storage.ItemType;
import io.storage.SingleItem;

@Controller
public class AddItemController {

	@RequestMapping("/addItem")
	@ResponseBody
	public String addItemtoStorage(HttpSession h) {
		if (App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())) {
			return "<!DOCTYPE html><html><body><p>Dodanie przedmiotu:</p>"
					+ "<form id='frm1' action='addItemSend'>"
					+ "Nazwa: <input type='text' name='name'><br>"
					+ "Opis: <input type='text' name='description'><br>"

			+ "<br><input type='button' onclick='myFunction()' value='Submit'></form>"

			+ "<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
					+ "</body></html>";
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}
	}

	@RequestMapping("/addItemSend")
	@ResponseBody
	public String addItemSend(HttpSession h, String name, boolean available, String description, ItemType itemtype) {
		if (App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())) {
			SingleItem newitem = new SingleItem(name, true, description, ItemType.tool);
			App.getInstance().getStorage().addObject(newitem);
			if (newitem != null) {
				return "Dodano przedmiot " + newitem.getName() + "<br><a href='/'>Wroc?</a>";
			} else {
				return "Niepowodzenie dodawania przedmiotu";
			}
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}
	}

	@RequestMapping("/ShowStorage")
	@ResponseBody
	public String ShowStorage(HttpSession h) {
		if (App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())) {
			return App.getInstance().getStorage().toString() + "<br><a href='/'>Wroc</a>";
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}
	}

	@RequestMapping("/removeItem")
	@ResponseBody
	public String removeItemFromStorage(HttpSession h) {
		if (App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())) {
			return "<!DOCTYPE html><html><body><p>Podaj nazwe przedmiotu do usuniecia:</p>"
					+ "<form id='frm1' action='removeItemSend'>"
					+ "Nazwa: <input type='text' name='name'><br>"

			+ "<br><input type='button' onclick='myFunction()' value='Submit'></form>"

			+ "<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
					+ "</body></html>";
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}
	}

	@RequestMapping("/removeItemSend")
	@ResponseBody
	public String removeItemSend(HttpSession h, String name) {
		if (App.getInstance().getUsers().isCurrentUserStorageWorker(h.getId())) {
			for (int i = 0; i < App.getInstance().getStorage().size(); i++) {
				if (App.getInstance().getStorage().GetObject(i).getName().toString().equals(name)) {
					App.getInstance().getStorage().removeObject(i);
				}
			}
			return "Usunieto przedmiot o nazwie " + name + "<br><a href='/'>Wroc</a>";
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}

	}

	@RequestMapping("/reserveMooringPlace")
	@ResponseBody
	public String reserveMoorningPlace(HttpSession h) {
		if (App.getInstance().getUsers().isCurrentUserNetUser(h.getId())) {
			return App.getInstance().getStorage().showSpecifyItems("mooringPlace")
					+ "<!DOCTYPE html><html><body><p>Podaj nazwe przedmiotu do rezerwacji:</p>"
					+ "<form id='frm1' action='reserveMooringPlaceSend'>"
					+ "Nazwa: <input type='text' name='name'><br>"

			+ "<br><input type='button' onclick='myFunction()' value='Submit'></form>"

			+ "<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
					+ "</body></html>" + "<br><a href='/'>Wroc</a>";
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}
	}

	@RequestMapping("/reserveMooringPlaceSend")
	@ResponseBody
	public String reserveMooringPlaceSend(HttpSession h, String name) {
		if (App.getInstance().getUsers().isCurrentUserNetUser(h.getId())) {
			String returnStatement = new String();
			for (int i = 0; i < App.getInstance().getStorage().size(); i++) {
				if (App.getInstance().getStorage().GetObject(i).getName().toString().equals(name)
						&& App.getInstance().getStorage().GetObject(i).isAvailable() == true) {
					App.getInstance().getStorage().GetObject(i).setAvailable(false);
					returnStatement = "Zarezerwowano miejsce o nazwie:  " + name + "<br><a href='/'>Wroc</a>";
					break;
				} else {
					returnStatement = "Miejsce jest niedostepne:  " + name + "<br><a href='/'>Wroc</a>";
				}
			}
			return returnStatement;

		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}

	}

	@RequestMapping("/reserveBoat")
	@ResponseBody
	public String reserveBoat(HttpSession h) {
		if (App.getInstance().getUsers().isCurrentUserNetUser(h.getId())) {
			return App.getInstance().getStorage().showSpecifyItems("boat")
					+ "<!DOCTYPE html><html><body><p>Podaj nazwe przedmiotu do rezerwacji:</p>"
					+ "<form id='frm1' action='reserveBoatSend'>"
					+ "Nazwa: <input type='text' name='name'><br>"

			+ "<br><input type='button' onclick='myFunction()' value='Submit'></form>"

			+ "<script> function myFunction() { document.getElementById('frm1').submit(); } </script>"
					+ "</body></html>" + "<br><a href='/'>Wroc</a>";
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}
	}

	@RequestMapping("/reserveBoatSend")
	@ResponseBody
	public String reserveBoatSend(HttpSession h, String name) {
		if (App.getInstance().getUsers().isCurrentUserNetUser(h.getId())) {
			String returnStatement = new String();
			for (int i = 0; i < App.getInstance().getStorage().size(); i++) {
				if (App.getInstance().getStorage().GetObject(i).getName().toString().equals(name)
						&& App.getInstance().getStorage().GetObject(i).isAvailable() == true) {
					App.getInstance().getStorage().GetObject(i).setAvailable(false);
					returnStatement = "Zarezerwowano lodke o nazwie:  " + name + "<br><a href='/'>Wroc</a>";
					break;
				} else {
					returnStatement = "Lodka jest niedostepna:  " + name + "<br><a href='/'>Wroc</a>";
				}
			}
			return returnStatement;
		} else {
			return "YOU DON'T HAVE MATCHING PERMISSIONS";
		}

	}

}