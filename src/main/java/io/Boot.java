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
import io.crew.Certificate;
import io.crew.Employee;
import io.crew.EmployeeAssignment;
import io.crew.SkillType;
import io.crew.UnassignableEmployeeException;
import io.general.*;
import io.storage.ItemType;
import io.storage.SingleItem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Boot {

  public static void main(String[] args) {
	  SpringApplication.run(Boot.class, args);
	    System.out.println("Hello World");
	    //new Boot().testConnect();
		App.getInstance().singletonTest();
		App.getInstance().getUsers().registerADMIN();
		App.getInstance().getUsers().registerServiceMan();
		App.getInstance().getUsers().registerNetUser("USER", new PersonalData(), "");
		

		LoginResult lr = App.getInstance().getUsers().netLogin(null, "USER", "");
		if (lr.success){
			System.out.println(App.getInstance().getUsers().getCurrentUser().getNick() + " == " + lr.loggedUser.getNick());
		}
		App.getInstance().getUsers().logout(null);
		
		lr = App.getInstance().getUsers().innerLogin(null, "ADMIN", "password");
		if (lr.success){
			System.out.println('\n' + App.getInstance().getUsers().getCurrentUser().getNick());
			System.out.println("Has admin permission: " + 
			App.getInstance().getUsers().doesCurrentUserHavePermission(null, PermissionType.admin));
			((InnerUser)(App.getInstance().getUsers().getCurrentUser())).getPermissions().addPermission(PermissionType.storageWorker);
			SingleItem Gwozdz = new SingleItem ("Mlotek", true, "mlotek Mirka", ItemType.tool);
	 		App.getInstance().getStorage().addObject(Gwozdz);		 		 		
	 		App.getInstance().getStorage().showStorage();
		}
		
		InnerUser iu = (InnerUser)(lr.loggedUser);
		if (iu != null){
			System.out.println("\nHas crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission(null, PermissionType.crewMaster));
			iu.getPermissions().addPermission(PermissionType.crewMaster);
			System.out.println("Has crewMaster permission: " + 
					App.getInstance().getUsers().doesCurrentUserHavePermission(null, PermissionType.crewMaster));
		}
		App.getInstance().getWorkshop().addingCommisionsTest();
		
		//Testy crew assignEmployee
		List<SkillType> skille = new ArrayList<SkillType>();
		skille.add(new SkillType(1, "Skill1"));
		skille.add(new SkillType(2, "Skill2"));
		
		SkillType skill = new SkillType(3, "Skill3");
		
		EmployeeAssignment test = new EmployeeAssignment(null, null, false, skille, null, null);
		Employee pracownik = new Employee();
		Certificate cert = new Certificate();
		cert.addSkill(skille.get(0));
		cert.addSkill(skille.get(1));
		
		pracownik.certificates.add(cert);
		try {
			App.getInstance().getCrew().assignEmployee(pracownik, test);
		} catch (UnassignableEmployeeException e) {
			System.err.println("Pracownik nie spelnia wymagan");
			e.printStackTrace();
		}
		System.out.println(test);
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
