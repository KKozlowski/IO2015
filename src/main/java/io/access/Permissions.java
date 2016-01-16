package io.access;

import io.general.App;
import io.models.PermissionsEntity;

public class Permissions {
	
	protected PermissionsEntity entity = new PermissionsEntity();

	public PermissionsEntity getEntity() {
		return entity;
	}

	public void setEntity(PermissionsEntity entity) {
		this.entity = entity;
	}

	public Boolean addPermission(PermissionType toAdd) {
		//if (!App.getInstance().getUsers().isCurrentUserAdmin()) //przenieś do Controllera
			//return false;
		
		if (entity.contains(toAdd))
			return false;
		else {
			entity.add(toAdd);
			App.getInstance().getUsers().getPermissionsController().update(entity);
			return true;
		}
	}

	public Boolean removePermission(PermissionType toRemove) {
		//if (!App.getInstance().getUsers().isCurrentUserAdmin()) //przenieś do Controllera
			//return false;
		
		if (entity.contains(toRemove)){
			if (toRemove == PermissionType.admin && App.getInstance().getUsers().numberOfADMINS() == 0)
				return false;
			entity.remove(toRemove);
			App.getInstance().getUsers().getPermissionsController().update(entity);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean contains(PermissionType pt){
		return entity.contains(pt);
	}

	public void serialize() {

	}
	
	public Permissions(){
		entity = new PermissionsEntity();
	}
	
	public Permissions(PermissionsEntity pe) {
		entity = pe;
	}
	
	public void create(){
		App.getInstance().getUsers().getPermissionsController().create(entity);
	}

}
