package io.access;

import io.models.UserEntity;

public class User {
	
	private static int lastID;
	
	private int id;


	private PersonalData personalData;
	
	private UserEntity entity;
	
	public User(String nick, PersonalData pd){
		entity = new UserEntity(nick, true);
		personalData = pd;
		id = ++lastID;
	}
	
	public boolean hasPermission(PermissionType pt){
		return false;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public int getID() {
		return id;
	}
	
	public String getNick(){
		return entity.getNick();
	}

	public void serialize() {

	}

}
