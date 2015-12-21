package io.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import io.access.InnerUser;
import io.crew.Employee;
import io.general.*;

public class Users {

	private ArrayList<User> users = new ArrayList<User>();

	private ArrayList<NetUser> netUsers = new ArrayList<NetUser>();

	private ArrayList<InnerUser> innerUsers = new ArrayList<InnerUser>();

	private PasswordStorage passwordStorage = new PasswordStorage();
	
	/**
	 * Deprecated.
	 */
	private User currentUser; 
	
	private HashMap<String, User> logins = new HashMap<String, User>();
	
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
		
		System.out.println("---");
		for (NetUser u : netUsers)
			System.out.println(u.getNick());
		
		return usr;
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

	private LoginResult login(String sessionID, User u, String password){
		if (u == null) 
			return new LoginResult(null, false);
		
		int id = u.getID();
		boolean success = passwordStorage.checkPassword(id, password);
		if (success){
			currentUser = u;
			logout(u.getID());
			logins.put(sessionID, u);
			System.out.println("LOGGED SESSION: "+ sessionID);
			return new LoginResult(u, success);
		}
		else{
			currentUser = null;
			return new LoginResult(null, false);
		}
	}
	
	public LoginResult netLogin(String sessionID, String nick, String password) {
		User u = getNetUserByNick(nick);
		System.out.println(u);
		
		return login(sessionID, u, password);
	}

	public LoginResult innerLogin(String sessionID, String nick, String password) {
		User u = getInnerUserByNick(nick);
		return login(sessionID, u, password);
	}
	
	public void logout(String sessionID){
		if (isUserLogged(sessionID))
			logins.remove(sessionID);
		currentUser = null;
	}
	
	public void logout(int userID){
		for (Entry<String, User> entry : logins.entrySet()) {
            if (entry.getValue().getID() == userID) {
                logout(entry.getKey());
                return;
            }
        }
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
			if (u.getNick().equals(n))
				return u;
		}
		return null;
	}
	
	public InnerUser getInnerUserByNick(String n) {
		for(InnerUser u : innerUsers){
			if (u.getNick().equals(n))
				return u;
		}
		return null;
	}
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	/**
	 * DEPRECATED
	 * @param pt
	 * @return
	 */
	public boolean doesCurrentUserHavePermission(PermissionType pt){
		if (currentUser == null)
			return false;
		return currentUser.hasPermission(pt);
	}
	
	/**
	 * TODO: Prawdziwa implementacja, z mapą sesja-user.
	 * @param sessionID ID bieżącej sesji. Na jej podstawie jest sprawdzany zalogowany w przeglądarce użytkownik.
	 * @param pt Typ uprawnienia dostępowego
	 * @return Czy użytkownik posiada dane uprawnienie
	 */
	public boolean doesCurrentUserHavePermission(String sessionID, PermissionType pt){
		if (getUserBySessionID(sessionID) == null)
			return false;
		return getUserBySessionID(sessionID).hasPermission(pt);
	}
	
	public boolean isCurrentUserAdmin(){
		if (currentUser == null && innerUsers.size() == 0)
			return true;
		else if (currentUser == null)
			return false;
		else return currentUser.hasPermission(PermissionType.admin);
	}
	
	public boolean isCurrentUserAdmin(String sessionID){
		if (innerUsers.size() == 0)
			return true;
		else if (getUserBySessionID(sessionID) == null)
			return false;
		else return getUserBySessionID(sessionID).hasPermission(PermissionType.admin);
	}
	
	private User getUserBySessionID(String sessionID){
		if (logins.containsKey(sessionID))
			return logins.get(sessionID);
		else 
			return null;
	}
	
	public int numberOfUsersWithPermission(PermissionType p){
		int result = 0;
		for(User u : users){
			if (u.hasPermission(p))
				result++;
		}
		return result;
	}
	
	public boolean isUserLogged(String sessionID){
		System.out.println("CHECKED SESSION: "+ sessionID);
		return logins.containsKey(sessionID);
	}

}
