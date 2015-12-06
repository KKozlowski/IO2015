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
		
		App.getInstance().getUsers().netLogin("HUE", "password");
		System.out.println(App.getInstance().getUsers().getCurrentUser().getNick());
		App.getInstance().getUsers().logout();
		
		App.getInstance().getUsers().innerLogin("ADMIN", "hwila");
		System.out.println(App.getInstance().getUsers().getCurrentUser().getNick());
		System.out.println(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.admin));
		SingleItem Gwozdz = new SingleItem ("M³otek", true, "m³otek Mirka", ItemType.tool);
		App.getInstance().getStorage().addObject(Gwozdz);
		App.getInstance().getStorage().showStorage();
	}

}
