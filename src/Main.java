import access.Users;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		App.getInstance().singletonTest();
		App.getInstance().getUsers().registerNetUser("HUE", null, "password");
	}

}
