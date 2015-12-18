package io.access;

import java.util.ArrayList;

import io.access.InnerUser;
import io.crew.Employee;
import io.general.*;

public class Users {

	private ArrayList<User> users = new ArrayList<User>();

	private ArrayList<NetUser> netUsers = new ArrayList<NetUser>();

	private ArrayList<InnerUser> innerUsers = new ArrayList<InnerUser>();

	private PasswordStorage passwordStorage = new PasswordStorage();
	
	private User currentUser;
	
	public InnerUser registerEmployee(String nick, PersonalData personalInfo, Permissions permissions, String password){
		
		Employee e = App.getInstance().getCrew().addEmployee();
		InnerUser result = addInnerUser(nick, personalInfo, e, permissions, password);
		if (result == null){
			App.getInstance().getCrew().removeEmployee(e);
		}
		e.setUserAccount(result);
		
		return result;
	}
	
	public InnerUser addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, Permissions permissions, String password) {
		if (getInnerUserByNick(nick) != null)
			return null;
		
		InnerUser iu = new InnerUser(nick, personalInfo, employmentInfo, permissions);
		
		if (innerUsers.size() == 0) 
			iu.getPermissions().addPermission(PermissionType.admin);
		
		passwordStorage.addIdPass(iu.getID(), password);
		
		users.add(iu);
		innerUsers.add(iu);
		
		return iu;
	}

	public InnerUser addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, String password) {
		return addInnerUser(nick, personalInfo, employmentInfo, new Permissions(), password);
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
	
	public boolean removeUserByNick(String nick){
		User u = getNetUserByNick(nick);
		return removeUser(u);
	}

	public boolean removeUserByID(int id) {
		User u = getUserByID(id);
		return removeUser(u);
	}
	
	private boolean removeUser(User u){
		if(u == null)
			return false;
		if(u == getCurrentUser())
			return false;
		if(!users.contains(u))
			return false;
		else{
			users.remove(u);
			if((NetUser)u != null){
				netUsers.remove(u);
			}
			if((InnerUser)u != null){
				innerUsers.remove(u);
			}
			return true;
		}
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
		else{
			currentUser = null;
			return new LoginResult(null, false);
		}
	}
	
	public LoginResult netLogin(String nick, String password) {
		User u = getNetUserByNick(nick);
		return login(u, password);
	}

	public LoginResult innerLogin(String nick, String password) {
		User u = getInnerUserByNick(nick);
		return login(u, password);
	}
	
	public void logout(){
		currentUser = null;
	}

	public void serialize() {

	}

	public User getUserByID(int id) {
		for(User u : users){
			if (u.getID() == id)
				return u;
		}
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
	
	public boolean doesCurrentUserHavePermission(PermissionType pt){
		if (currentUser == null)
			return false;
		return currentUser.hasPermission(pt);
	}
	
	public boolean isCurrentUserAdmin(){
		if (currentUser == null && innerUsers.size() == 0)
			return true;
		else if (currentUser == null)
			return false;
		else return currentUser.hasPermission(PermissionType.admin);
	}
	
	public int numberOfUsersWithPermission(PermissionType p){
		int result = 0;
		for(User u : users){
			if (u.hasPermission(p))
				result++;
		}
		return result;
	}

}
