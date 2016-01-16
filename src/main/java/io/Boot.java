package io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.workshop.FixCommision;
import io.access.InnerUser;
import io.access.LoginResult;
import io.access.PermissionType;
import io.access.Permissions;
import io.access.PersonalData;
import io.access.Users;
import io.general.*;
import io.models.PermissionsEntity;
import io.storage.ItemType;
import io.storage.SingleItem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Boot {

  public static void main(String[] args) {
	  SpringApplication.run(Boot.class, args);
	    System.out.println("Hello World");
	    //new Boot().testConnect();
		App.getInstance().singletonTest();
		App.getInstance().getUsers().registerADMIN();
		App.getInstance().getUsers().registerNetUser("USER", new PersonalData("imie", "nazwisko", "123456", "adres", "654321", "mail@mail.com"), "pass");
		App.getInstance().getUsers().registerEmployee("EMPLOYEE", new PersonalData("imie2", "nazwisko2", "2123456", "adres2", "2654321", "mail2@mail.com"), new Permissions(new PermissionsEntity("010100")), "pass2");

		LoginResult lr = App.getInstance().getUsers().innerLogin("A", "EMPLOYEE", "pass2");
		if (lr.success){
			System.out.println(App.getInstance().getUsers().getUserBySessionID("A").getNick() + " == " + lr.loggedUser.getNick());
			System.out.println("check" + lr.loggedUser.getPersonalData().getName());
			System.out.println("check" + App.getInstance().getUsers().getUserBySessionID("A").getPersonalData().getName());
		}
				

		lr = App.getInstance().getUsers().netLogin("A", "USER", "pass");
		if (lr.success){
			System.out.println(App.getInstance().getUsers().getUserBySessionID("A").getNick() + " == " + lr.loggedUser.getNick());
			System.out.println("check" + lr.loggedUser.getPersonalData().getName());
			System.out.println("check" + App.getInstance().getUsers().getUserBySessionID("A").getPersonalData().getName());
		}
		App.getInstance().getUsers().logout("A");
		
		lr = App.getInstance().getUsers().innerLogin("A", "ADMIN", "password");
		if (lr.success){
			System.out.println('\n' + App.getInstance().getUsers().getCurrentUser().getNick());
			System.out.println("Has admin permission: " + 
			App.getInstance().getUsers().doesCurrentUserHavePermission("A", PermissionType.admin));
			((InnerUser)(App.getInstance().getUsers().getCurrentUser())).getPermissions().addPermission(PermissionType.storageWorker);
			SingleItem Gwozdz = new SingleItem ("Mlotek", true, "mlotek Mirka", ItemType.tool);
	 		App.getInstance().getStorage().addObject(Gwozdz);		 		 		
	 		App.getInstance().getStorage().showStorage();
		}
		
		InnerUser iu = (InnerUser)(lr.loggedUser);
		if (iu != null){
			System.out.println("\nHas crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission("A", PermissionType.crewMaster));
			iu.getPermissions().addPermission(PermissionType.crewMaster);
			System.out.println("Has crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission("A", PermissionType.crewMaster));
		}
		App.getInstance().getWorkshop().addingCommisionsTest();
  }
	
	public void testConnect() {
	    String dbUrl = "jdbc:mysql://localhost/world";
	    String dbClass = "com.mysql.jdbc.Driver";
	    String query = "Select distinct(table_name) from INFORMATION_SCHEMA.TABLES";
	    String username = "root";
	    String password = "MyNewPass";
	    try {

	        Class.forName(dbClass);
	        Connection connection = DriverManager.getConnection(dbUrl,
	            username, password);
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);
	        while (resultSet.next()) {
	        String tableName = resultSet.getString(1);
	        System.out.println("Table name : " + tableName);
	        }
	        connection.close();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    }
}
