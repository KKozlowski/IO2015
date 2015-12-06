import access.InnerUser;
import access.LoginResult;
import access.PermissionType;
import access.Permissions;
import access.Users;
import general.*;
import storage.ItemType;
import storage.SingleItem;
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		App.getInstance().singletonTest();
		App.getInstance().getUsers().registerNetUser("HUE", null, "password");
		App.getInstance().getUsers().registerEmployee("ADMIN", null, new Permissions(), "hwila");
		
		LoginResult lr = App.getInstance().getUsers().netLogin("HUE", "password");
		if (lr.success){
			System.out.println(App.getInstance().getUsers().getCurrentUser().getNick() + " == " + lr.loggedUser.getNick());
		}
		App.getInstance().getUsers().logout();
		

		App.getInstance().getUsers().innerLogin("ADMIN", "hwila");
		System.out.println(App.getInstance().getUsers().getCurrentUser().getNick());
		System.out.println(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.admin));
		SingleItem Gwozdz = new SingleItem ("M³otek", true, "m³otek Mirka", ItemType.tool);
		App.getInstance().getStorage().addObject(Gwozdz);
		App.getInstance().getStorage().showStorage();

		lr = App.getInstance().getUsers().innerLogin("ADMIN", "hwila");
		if (lr.success){
			System.out.println('\n' + App.getInstance().getUsers().getCurrentUser().getNick());
			System.out.println("Has admin permission: " + 
			App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.admin));
		}
		
		InnerUser iu = (InnerUser)(lr.loggedUser);
		if (iu != null){
			System.out.println("\nHas crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster));
			iu.getPermissions().addPermission(PermissionType.crewMaster);
			System.out.println("Has crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster));
		}
	}

}
