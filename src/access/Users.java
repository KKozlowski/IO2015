package access;

import java.util.ArrayList;
import java.util.List;

import crew.Employee;

public class Users {

	private ArrayList<User> users = new ArrayList<User>();

	private ArrayList<NetUser> netUsers = new ArrayList<NetUser>();

	private ArrayList<InnerUser> innerUsers = new ArrayList<InnerUser>();

	private PasswordStorage passwordStorage = new PasswordStorage();
	
	private User currentUser;

	public boolean addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, Permissions permissions, String password) {
		return false;
	}

	public boolean addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, String password) {
		return false;
	}

	public NetUser registerNetUser(String nick, PersonalData personalInfo, String password) {
		if (getNetUserByNick(nick) != null)
			return null;
		
		NetUser usr = new NetUser(nick, personalInfo);
		int newID = usr.getID();
		passwordStorage.addIdPass(newID, password);
		
		users.add(usr);
		netUsers.add(usr);
		
		return null;
	}

	public boolean removeUserByID() {
		return false;
	}

	private LoginResult login(User u, String password){
		if (u == null) 
			return new LoginResult(null, false);
		
		int id = u.getID();
		boolean success = passwordStorage.checkPassword(id, password);
		if (success){
			currentUser = u;
			return new LoginResult(u, success);
		}
		else
			return new LoginResult(null, false);
	}
	
	public LoginResult netLogin(String nick, String password) {
		User u = getNetUserByNick(nick);
		return login(u, password);
	}

	public LoginResult innerLogin(String nick, String password) {
		User u = getInnerUserByNick(nick);
		return login(u, password);
	}

	public void serialize() {

	}

	public User getUserByID() {
		return null;
	}

	public NetUser getNetUserByNick(String n) {
		for(NetUser u : netUsers){
			if (u.getNick() == n)
				return u;
		}
		return null;
	}
	
	public InnerUser getInnerUserByNick(String n) {
		for(InnerUser u : innerUsers){
			if (u.getNick() == n)
				return u;
		}
		return null;
	}
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	public Boolean doesCurrentUserHavePermission(PermissionType pt){
		return currentUser.getAllPermissions().contains(pt);
	}

}
