package io.general;


import io.access.Users;
import io.controllers.Controllers;
import io.crew.EmployeeAssignment;
import io.crew.StaffDeployment;
import io.services.Services;
import io.storage.Storage;
import io.workshop.Workshop;

public class App {
	private static App instance;
	
	private Users users;
	private StaffDeployment crew;
	private Services services;
	private Storage storage;
	private Workshop workshop;
	private Controllers controllers = new Controllers();
	
	protected App() {
	      // Exists only to defeat instantiation.
    }
	
	public static void Start(){
		if(instance == null) {
          instance = new App();
          instance.users = new Users();
          instance.crew = new StaffDeployment();
          instance.services = new Services();
          instance.storage = new Storage();
          instance.workshop = new Workshop();
          instance.controllers = new Controllers();
       }
	}
	
    public static App getInstance() {
	   if(instance == null)
			Start();
       return instance;
    }
    
    public void singletonTest(){
    	System.out.println("Singleton nazywa się App.");
    }

	public Users getUsers() {
		return users;
	}

	public StaffDeployment getCrew() {
		return crew;
	}

	public Services getServices() {
		return services;
	}

	public Storage getStorage() {
		return storage;
	}
	
	public Workshop getWorkshop() {
		return workshop;
	}
	
	public Controllers getControllers(){
		return controllers;
	}
    
}
