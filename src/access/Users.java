package access;

import java.util.ArrayList;
import java.util.List;

import crew.Employee;

public class Users {

	private ArrayList<User> users = new ArrayList<User>();

	private ArrayList<NetUser> netUsers = new ArrayList<NetUser>();

	private ArrayList<InnerUser> innerUsers = new ArrayList<InnerUser>();

	private PasswordStorage passwordStorage = new PasswordStorage();

	public boolean addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, Permissions permissions, String password) {
		return false;
	}

	public boolean addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, String password) {
		return false;
	}

	public NetUser registerNetUser(String nick, PersonalData personalInfo, String password) {
		if (getUserByNick(nick) != null)
			return null;
		
		User usr = new User(nick, personalInfo);
		int newID = usr.getID();
		passwordStorage.addIdPass(newID, password);
		
		users.add(usr);
		
		return null;
	}

	public boolean removeUserByID() {
		return false;
	}

	public LoginResult netLogin(String nick, String password) {
		return null;
	}

	public LoginResult innerLogin(String nick, String password) {
		return null;
	}

	public void serialize() {

	}

	public User getUserByID() {
		return null;
	}

	public User getUserByNick(String n) {
		return null;
	}
	
	public User getCurrentUser(){
		return null;
	}
	
	public Boolean doesCurrentUserHavePermission(){
		return true;
	}

}
