package io.access;

import io.access.controllers.PersonalDataController;
import io.access.controllers.UserController;
import io.access.models.PersonalDataEntity;
import io.access.models.UserEntity;
import io.general.App;

public class User {
	
	private static int lastID;
	
	private int id;
	
	private PersonalData personalData;
	
	protected UserEntity entity;
	
	public static User retrieveUserByNick(String nick){
		UserController uc = App.getInstance().getUsers().getUserController();
		PersonalDataController pdc = App.getInstance().getUsers().getPersonalDataController();
		UserEntity ue = uc.getByNick(nick);
		if (ue == null) 
			return null;
		int id = ue.getId();
		PersonalDataEntity pde = pdc.getById(id);
		PersonalData pd = new PersonalData(pde);
		
		return new User(ue, pd);
	}
	
	public static User retrieveUserById(int id){
		UserController uc = App.getInstance().getUsers().getUserController();
		PersonalDataController pdc = App.getInstance().getUsers().getPersonalDataController();
		UserEntity ue = uc.getById(id);
		if (ue == null) 
			return null;
		PersonalDataEntity pde = pdc.getById(id);
		PersonalData pd = new PersonalData(pde);
		
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
		UserController uc = App.getInstance().getUsers().getUserController();
		if (uc.getByNick(entity.getNick()) != null)
			throw new DuplicateNickException();
		entity = uc.create(entity);
		//App.getInstance().getUsers().personalDatas.put(entity.getId(), personalData);
		personalData.getEntity().setUserID(entity.getId());
		personalData.create();
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
	
	public boolean isNetUser(){
		return entity.isNetUser();
	}

}
