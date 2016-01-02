package io.access;

public class NetUser extends User {

	public NetUser(String nick, PersonalData pd) {
		super(nick, pd, true);
		// TODO Auto-generated constructor stub
	}
	
	public NetUser(User u){
		super(u);
	}
	
	public static NetUser retrieveNetUserByNick(String nick){
		User u = retrieveUserByNick(nick);
		if (u == null)
			return null;
		else{
			System.out.println("FOUND NICK: " + u.getNick());
			NetUser iu = new NetUser(u);
			return iu;
		}
	}

	@Override
	public boolean hasPermission(PermissionType pt){
		if (pt == PermissionType.netUser) 
			return true;
		else
			return false;
	}
}
