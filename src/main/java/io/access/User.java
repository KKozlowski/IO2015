package io.access;


public class User {
	
	private static int lastID;
	
	private int id;

	private String nick;

	private PersonalData personalData;
	
	public User(String nick, PersonalData pd){
		this.nick = nick;
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
		return nick;
	}

	public void serialize() {

	}

}
