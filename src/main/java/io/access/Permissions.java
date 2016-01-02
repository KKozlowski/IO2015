package io.access;

import java.util.ArrayList;

import io.general.App;

public class Permissions {

	private ArrayList<PermissionType> permissions = new ArrayList<PermissionType>();

	public Boolean addPermission(PermissionType toAdd) {
		//if (!App.getInstance().getUsers().isCurrentUserAdmin()) //przenieś do Controllera
			//return false;
		
		if (permissions.contains(toAdd))
			return false;
		else {
			permissions.add(toAdd);
			return true;
		}
	}

	public Boolean removePermission(PermissionType toRemove) {
		//if (!App.getInstance().getUsers().isCurrentUserAdmin()) //przenieś do Controllera
			//return false;
		
		if (permissions.contains(toRemove)){
			if (toRemove == PermissionType.admin && App.getInstance().getUsers().numberOfADMINS() == 0)
				return false;
			permissions.remove(toRemove);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean contains(PermissionType pt){
		return permissions.contains(pt);
	}

	public void serialize() {

	}
	
	public Permissions(){
		
	}

}
