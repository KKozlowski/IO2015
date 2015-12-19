package io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.workshop.FixCommision;
import io.access.InnerUser;
import io.access.LoginResult;
import io.access.PermissionType;
import io.access.Permissions;
import io.access.Users;
import io.general.*;
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
	    System.out.println("Hello World");
		App.getInstance().singletonTest();
		App.getInstance().getUsers().registerNetUser("HUE", null, "password");
		App.getInstance().getUsers().registerEmployee("ADMIN", null, new Permissions(), "hwila");
		
	
		
		
		
		LoginResult lr = App.getInstance().getUsers().netLogin("HUE", "password");
		if (lr.success){
			System.out.println(App.getInstance().getUsers().getCurrentUser().getNick() + " == " + lr.loggedUser.getNick());
		}
		App.getInstance().getUsers().logout();
		
		lr = App.getInstance().getUsers().innerLogin("ADMIN", "hwila");
		if (lr.success){
			System.out.println('\n' + App.getInstance().getUsers().getCurrentUser().getNick());
			System.out.println("Has admin permission: " + 
			App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.admin));
			((InnerUser)(App.getInstance().getUsers().getCurrentUser())).getPermissions().addPermission(PermissionType.storageWorker);
			SingleItem Gwozdz = new SingleItem ("Mlotek", true, "mlotek Mirka", ItemType.tool);
	 		App.getInstance().getStorage().addObject(Gwozdz);		 		 		
	 		App.getInstance().getStorage().showStorage();
		}
		
		InnerUser iu = (InnerUser)(lr.loggedUser);
		if (iu != null){
			System.out.println("\nHas crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster));
			iu.getPermissions().addPermission(PermissionType.crewMaster);
			System.out.println("Has crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster));
		}
		App.getInstance().getWorkshop().addingCommisionsTest();
		
		new Boot().testConnect();
		
		
		
		
		SpringApplication.run(Boot.class, args);
		
		App.getInstance().getUsers().registerNetUser("HW", null, "password");
		App.getInstance().getUsers().registerNetUser("HW", null, "password");
  }
	
	public void testConnect() {
	    String dbUrl = "jdbc:mysql://localhost/world";
	    String dbClass = "com.mysql.jdbc.Driver";
	    String query = "Select distinct(table_name) from INFORMATION_SCHEMA.TABLES";
	    String username = "root";
	    String password = "ultramaryna";
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
