package io.access;

import io.access.controllers.UserController;
import io.general.App;
import io.models.UserEntity;

public class User {
	
	private static int lastID;
	
	private int id;


	private PersonalData personalData;
	
	protected UserEntity entity;
	
	public static User retrieveUserByNick(String nick){
		UserController uc = App.getInstance().getControllers().users;
		UserEntity ue = uc.getByNick(nick);
		if (ue == null) return null;
		PersonalData pd = App.getInstance().getUsers().personalDatas.get(ue.getId());
		
		return new User(ue, pd);
	}
	
	public static User retrieveUserById(int id){
		UserController uc = App.getInstance().getControllers().users;
		UserEntity ue = uc.getById(id);
		if (ue == null) return null;
		PersonalData pd = App.getInstance().getUsers().personalDatas.get(ue.getId());
		
		return new User(ue, pd);
	}
	
	public User(String nick, PersonalData pd, boolean isNet) {
		entity = new UserEntity(nick, isNet);
		personalData = pd;
	}
	
	public User(UserEntity ue, PersonalData pd){
		entity = ue;
		personalData = pd;
	}
	
	protected User(User u){
		entity = u.entity;
		personalData = u.personalData;
	}
	
	public void create() throws DuplicateNickException{
		UserController uc = App.getInstance().getControllers().users;
		if (uc.getByNick(entity.getNick()) != null)
			throw new DuplicateNickException();
		entity = App.getInstance().getControllers().users.create(entity);
		App.getInstance().getUsers().personalDatas.put(entity.getId(), personalData);
	}
	
	public boolean hasPermission(PermissionType pt){
		return false;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public int getID() {
		return entity.getId();
	}
	
	public String getNick(){
		return entity.getNick();
	}

	public void serialize() {

	}

}
