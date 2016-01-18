package io.storage.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.access.PermissionType;
import io.general.App;
import io.storage.ItemType;
import io.storage.SingleItem;


@Controller
public class StorageController {
	@RequestMapping(value = "/storage")
	 @ResponseBody
	public String storage(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.storageWorker))
		
			return "Witaj w magazynie"
					+"<br><br><a href='/addItem'>Dodaj sprz�t</a>"
					+"<br><a href='/removeItem'>Usu� sprz�t</a>"
					+"<br><a href='/ShowStorage'>Wy�wietl ca�y sprz�t</a>";
		
		else 
			 return "YOU DON'T HAVE MATCHING PERMISSIONS";
	
	}
	
	
	
}
