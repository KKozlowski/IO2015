package access;

import java.util.List;

import crew.Employee;

public class Users {

	private List<User> users;

	private List<NetUser> netUsers;

	private List<InnerUser> innerUsers;

	private PasswordStorage passwordStorage;

	public boolean addInnerUser(PersonalData personalInfo, Employee employmentInfo, Permissions permissions, String password) {
		return false;
	}

	public boolean addInnerUser(PersonalData personalInfo, Employee employmentInfo, String password) {
		return false;
	}

	public NetUser registerNetUser(PersonalData personalInfo, String password) {
		return null;
	}

	public boolean removeUserByID() {
		return false;
	}

	public LoginResult netLogin(String nick, String password) {
		return null;
	}

	public LoginResult innerLogin(String nick, String password) {
		return null;
	}

	public void serialize() {

	}

	public User getUserByID() {
		return null;
	}

	public User getUserByNick() {
		return null;
	}

}
