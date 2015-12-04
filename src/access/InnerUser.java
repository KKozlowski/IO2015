package access;

import crew.Employee;

public class InnerUser extends User {

	public InnerUser(String nick, PersonalData pd) {
		super(nick, pd);
		// TODO Auto-generated constructor stub
	}

	private Employee employmentInfo;

	private Permissions permissions;

	public Employee getEmploymentInfo() {
		return null;
	}

	public Permissions getPermissions() {
		return null;
	}

}
