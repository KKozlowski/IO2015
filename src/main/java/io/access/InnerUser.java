package io.access;

import io.access.controllers.UserController;
import io.crew.Employee;
import io.general.App;
import io.models.UserEntity;

public class InnerUser extends User {

	public InnerUser(String nick, PersonalData pd, Employee e, Permissions p){
		super(nick, pd, false);
		employmentInfo = e;
		permissions = p;
	}
	
	public static InnerUser retrieveInnerUserByNick(String nick){
		UserController uc = App.getInstance().getUsers().getUserController();
		User u = retrieveUserByNick(nick);
		if (u == null)
			return null;
		else{
			System.out.println("FOUND NICK: " + u.getNick());
			InnerUser iu = new InnerUser(u);
			iu.permissions = App.getInstance().getUsers().permissions.get(iu.getID());
			return iu;
		}
		
		
	}

	private Employee employmentInfo;

	Permissions permissions;
	
	public InnerUser(User u){
		super(u);
	}
	
	@Override
	public void create() throws DuplicateNickException{
		super.create();
		App.getInstance().getUsers().permissions.put(entity.getId(), permissions);
	}
	
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
	public void setEmploymentInfo(Employee employee){
		employmentInfo=employee;
	}

}
