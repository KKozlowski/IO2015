package io.access;

public class LoginResult {

	public boolean success;

	public User loggedUser;
	
	public LoginResult(User u, boolean s){
		loggedUser = u;
		success = s;
	}
	
}
