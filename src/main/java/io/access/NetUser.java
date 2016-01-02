package io.access;

public class NetUser extends User {

	public NetUser(String nick, PersonalData pd) {
		super(nick, pd, true);
		// TODO Auto-generated constructor stub
	}
	
	public NetUser(User u){
		super(u);
	}

	@Override
	public boolean hasPermission(PermissionType pt){
		if (pt == PermissionType.netUser) 
			return true;
		else
			return false;
	}
}
