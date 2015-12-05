package access;

import java.util.ArrayList;
import java.util.List;

import general.App;

public class Permissions {

	private ArrayList<PermissionType> permissions = new ArrayList<PermissionType>();

	public Boolean addPermission(PermissionType toAdd) {
		if (!App.getInstance().getUsers().isCurrentUserAdmin())
			return false;
		
		if (permissions.contains(toAdd))
			return false;
		else {
			permissions.add(toAdd);
			return true;
		}
	}

	public Boolean removePermission(PermissionType toAdd) {
		if (!App.getInstance().getUsers().isCurrentUserAdmin())
			return false;
		
		if (permissions.contains(toAdd)){
			permissions.remove(toAdd);
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
