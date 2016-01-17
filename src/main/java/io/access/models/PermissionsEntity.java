package io.access.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.access.PermissionType;

@Entity
@Table(name = "permissions")
public class PermissionsEntity {
	
	@Id
	protected int userID;
	
	@NotNull
	private String permissions = "000000";
	
	public PermissionsEntity() {
		permissions = "000000";
	}
	
	public PermissionsEntity(String p) {
		permissions = p;
	}
	
	public ArrayList<PermissionType> getPermissions() {
		ArrayList<PermissionType> permissionarray = new ArrayList<PermissionType>();
		for (int i = 0; i < permissions.length(); i++) {
			if (permissions.charAt(i) == '1') {
				permissionarray.add(PermissionType.values()[i]);
			}
		}
		return permissionarray;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void add(PermissionType toAdd) {
		int i = toAdd.ordinal();
		char[] permissionsarray = permissions.toCharArray();
		permissionsarray[i] = '1';
		permissions = String.valueOf(permissionsarray);
	}

	public void remove(PermissionType toRemove) {
		int i = toRemove.ordinal();
		char[] permissionsarray = permissions.toCharArray();
		permissionsarray[i] = '0';
		permissions = String.valueOf(permissionsarray);
	}

	public boolean contains(PermissionType toRemove) {
		int i = toRemove.ordinal();
		char[] permissionsarray = permissions.toCharArray();
		if(permissionsarray[i] == '1')
			return true;
		else
			return false;
	}
}
