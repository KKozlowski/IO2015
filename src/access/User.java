package access;

import java.util.Collection;

public class User {

	private int id;

	private String nick;

	private PersonalData personalData;
	
	public User(String nick, PersonalData pd){
		this.nick = nick;
		personalData = pd;
	}
	
	public Collection<PermissionType> getAllPermissions() {
		return null;
	}

	public PersonalData getPersonalData() {
		return null;
	}

	public int getID() {
		return 0;
	}
	
	public String getNick(){
		return nick;
	}

	public void serialize() {

	}

}
