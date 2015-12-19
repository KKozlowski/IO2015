package io.access;

public class LoginResult {

	public boolean success;

	public User loggedUser;
	
	public LoginResult(User u, boolean s){
		loggedUser = u;
		success = s;
	}
	
	@Override
	public String toString(){
		if (success)
			return "Login succeeded for user " + loggedUser.getNick();
		else
			return "Login failed";
	}
	
}
