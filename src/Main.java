import access.Users;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		App.getInstance().singletonTest();
		App.getInstance().getUsers().registerNetUser("HUE", null, "password");
		App.getInstance().getUsers().netLogin("HUE", "password");
		System.out.println(App.getInstance().getUsers().getCurrentUser().getNick());
	}

}
