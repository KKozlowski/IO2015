package io.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import io.access.InnerUser;
import io.access.PermissionType;
import io.access.Permissions;
import io.access.PersonalData;
import io.access.controllers.UserController;
import io.access.models.PermissionsEntity;
import io.access.controllers.PersonalDataController;
import io.access.controllers.IdPassPairController;
import io.access.controllers.PermissionsController;
import io.crew.Employee;
import io.general.*;

public class Users {

	private ArrayList<User> users = new ArrayList<User>();

	private ArrayList<NetUser> netUsers = new ArrayList<NetUser>();

	private ArrayList<InnerUser> innerUsers = new ArrayList<InnerUser>();

	private PasswordStorage passwordStorage = new PasswordStorage();
	
	private UserController userController = null;
	private PersonalDataController personalDataController = null;
	private PermissionsController permissionsController = null;
	private IdPassPairController idPassPairController = null;
	
	/**
	 * Deprecated.
	 */
	private User currentUser; 
	
	private HashMap<String, User> logins = new HashMap<String, User>();
	
	public InnerUser registerADMIN(){
		Employee e = App.getInstance().getCrew().addEmployee();
		InnerUser result = addInnerUser("ADMIN", new PersonalData(), e, new Permissions(new PermissionsEntity("001001")), "password");
		if (result == null){
			System.out.println("NULLOLO");
			App.getInstance().getCrew().removeEmployee(e);
		}
		e.setUserAccount(result);
		return result;
	}
	
	public InnerUser registerEmployee(String nick, PersonalData personalInfo, Permissions permissions, String password){
		System.out.println(nick);
		Employee e = App.getInstance().getCrew().addEmployee();
		InnerUser result = addInnerUser(nick, personalInfo, e, permissions, password);
		
		if (result == null){
			System.out.println("NULLOLO");
			App.getInstance().getCrew().removeEmployee(e);
		}
		e.setUserAccount(result);
		
		return result;
	}
	
	public InnerUser registerStorageWorker(){
		Employee e = App.getInstance().getCrew().addEmployee();
		InnerUser result = addInnerUser("Worker", new PersonalData(), e, new Permissions(new PermissionsEntity("001111")), "haslo");
		if (result == null){
			System.out.println("sth");
			App.getInstance().getCrew().removeEmployee(e);
		}
		e.setUserAccount(result);
		return result;
	}
	
	public InnerUser addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, Permissions permissions, String password) {
		InnerUser iu;
		
		if (getInnerUserByNick(nick) != null){
			System.out.println("NOTNULL");
			return null;
		}
		iu = new InnerUser(nick, personalInfo, employmentInfo, permissions);
		try{
			iu.create();
		} catch (DuplicateNickException dne){
			System.out.println("DUPLICATE EXCEPTION");
			return null;
		}
		
		passwordStorage.addIdPass(iu.getID(), password);
		
		users.add(iu);
		innerUsers.add(iu);
		
		return iu;
	}

	public InnerUser addInnerUser(String nick, PersonalData personalInfo, Employee employmentInfo, String password) {
		return addInnerUser(nick, personalInfo, employmentInfo, new Permissions(), password);
	}
	
	public boolean setUserController(io.access.controllers.UserController uc){
		if (userController == null){
			userController = uc;
			return true;
		}
		else {
			return false;
		}
	}
	
	public UserController getUserController(){
		return userController;
	}
	
	public boolean setPersonalDataController(io.access.controllers.PersonalDataController pdc){
		if (personalDataController == null){
			personalDataController = pdc;
			return true;
		}
		else {
			return false;
		}
	}
	
	public PersonalDataController getPersonalDataController(){
		return personalDataController;
	}
	
	public boolean setPermissionsController(io.access.controllers.PermissionsController pc){
		if (permissionsController == null){
			permissionsController = pc;
			return true;
		}
		else {
			return false;
		}
	}
	
	public PermissionsController getPermissionsController(){
		return permissionsController;
	}
	
	public boolean setIdPassPairController(io.access.controllers.IdPassPairController ippc){
		if (idPassPairController == null){
			idPassPairController = ippc;
			return true;
		}
		else {
			return false;
		}
	}
	
	public IdPassPairController getIdPassPairController(){
		return idPassPairController;
	}

	public NetUser registerNetUser(String nick, PersonalData personalInfo, String password) {
		NetUser usr;
		if (getNetUserByNick(nick) != null)
			return null;
		usr = new NetUser(nick, personalInfo);
		try{
			usr.create();
		} catch (DuplicateNickException dne) {
			return null;
		}
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
		if (u == null) {
			System.out.println("LoginResult u==null");
			return new LoginResult(null, false);
		}
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
		//if ()
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

	public User getUserByID(int id) {
		for(User u : users){
			if (u.getID() == id)
				return u;
		}
		return null;
	}

	public NetUser getNetUserByNick(String n) {
		NetUser u = NetUser.retrieveNetUserByNick(n);
		if (u != null && u.isNetUser())
			return u;
		else
			return null;
	}
	
	public InnerUser getInnerUserByNick(String n) {
		InnerUser u = InnerUser.retrieveInnerUserByNick(n);
		if (u != null && !u.isNetUser())
			return u;
		else
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
	
	/**
	 * DEPRECATED
	 * @return
	 */
	public boolean isCurrentUserAdmin(){
		if (currentUser == null)
			return false;
		else return currentUser.hasPermission(PermissionType.admin);
	}
	
	public boolean isCurrentUserStorageWorker(String sessionID){
		if (getUserBySessionID(sessionID) == null)
			return false;
		else return getUserBySessionID(sessionID).hasPermission(PermissionType.storageWorker);
	}
	
	
	public boolean isCurrentUserAdmin(String sessionID){
		if (getUserBySessionID(sessionID) == null)
			return false;
		else return getUserBySessionID(sessionID).hasPermission(PermissionType.admin);
	}
	
	

	public boolean isCurrentUserNetUser(String sessionID) {
		if (getUserBySessionID(sessionID) == null) {
			return false;
		} else {
			return getUserBySessionID(sessionID).hasPermission(PermissionType.netUser);
		}
	}
	
	public User getUserBySessionID(String sessionID){
		if (logins.containsKey(sessionID))
			return logins.get(sessionID);
		else 
			return null;
	}
	
	public int numberOfADMINS(){
		int result = 0;
		for(User u : users){
			if (u.hasPermission(PermissionType.admin))
				result++;
		}
		return result;
	}
	
	public boolean isUserLogged(String sessionID){
		System.out.println("CHECKED SESSION: "+ sessionID);
		return logins.containsKey(sessionID);
	}
	
	public boolean changePassword(int id, String password){
		return passwordStorage.changePassword(id, password);
	}

}
