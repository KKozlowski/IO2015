import access.Users;
import crew.EmployeeAssignment;
import services.Services;
import storage.Storage;
import workshop.Workshop;

public class App {
	private static App instance;
	
	private Users users;
	private EmployeeAssignment crew;
	private Services services;
	private Storage storage;
	private Workshop workshop;
	
	protected App() {
	      // Exists only to defeat instantiation.
    }
	
	public static void Start(){
		if(instance == null) {
          instance = new App();
          instance.users = new Users();
          instance.crew = new EmployeeAssignment();
          instance.services = new Services();
          instance.storage = new Storage();
          instance.workshop = new Workshop();
       }
	}
	
    public static App getInstance() {
	   if(instance == null)
			Start();
       return instance;
    }
    
    public void singletonTest(){
    	System.out.println("Singleton nazywa siê App.");
    }

	public Users getUsers() {
		return users;
	}

	public EmployeeAssignment getCrew() {
		return crew;
	}

	public Services getServices() {
		return services;
	}

	public Storage getStorage() {
		return storage;
	}
    
}
