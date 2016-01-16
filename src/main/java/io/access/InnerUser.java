package io.access;

import io.access.controllers.PermissionsController;
import io.access.controllers.UserController;
import io.crew.Employee;
import io.general.App;
import io.models.PermissionsEntity;
import io.models.UserEntity;

public class InnerUser extends User {

	public InnerUser(String nick, PersonalData pd, Employee e, Permissions p){
		super(nick, pd, false);
		employmentInfo = e;
		permissions = p;
	}
	
	public static InnerUser retrieveInnerUserByNick(String nick){
		UserController uc = App.getInstance().getUsers().getUserController();
		PermissionsController pc = App.getInstance().getUsers().getPermissionsController();
		User u = retrieveUserByNick(nick);
		if (u == null)
			return null;
		else{
			System.out.println("FOUND NICK: " + u.getNick());
			InnerUser iu = new InnerUser(u);
			int id = u.getID();
			PermissionsEntity pe = pc.getById(id);
			//iu.permissions = App.getInstance().getUsers().permissions.get(iu.getID());
			iu.permissions = new Permissions(pe);
			return iu;
		}
		
		
	}

	private Employee employmentInfo;

	Permissions permissions;
	
	public InnerUser(User u){
		super(u);
		permissions = new Permissions();
	}
	
	@Override
	public void create() throws DuplicateNickException{
		super.create();
		//App.getInstance().getUsers().permissions.put(entity.getId(), permissions);
		permissions.getEntity().setUserID(entity.getId());
		permissions.create();
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
