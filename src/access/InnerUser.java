package access;

import crew.Employee;

public class InnerUser extends User {

	public InnerUser(String nick, PersonalData pd, Employee e, Permissions p) {
		super(nick, pd);
		employmentInfo = e;
		permissions = p;
	}

	private Employee employmentInfo;

	private Permissions permissions;
	
	@Override
	public boolean hasPermission(PermissionType pt){
		return permissions.contains(pt);
	}
	
	public Employee getEmploymentInfo() {
		return employmentInfo;
	}

	public Permissions getPermissions() {
		return permissions;
	}

}
