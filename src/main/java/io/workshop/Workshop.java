package io.workshop;

import java.util.ArrayList;
import java.util.List;

import io.crew.EmployeeAssignment;
import io.general.App;
import io.storage.ItemAssignment;
import io.storage.SingleItem;

public class Workshop {

	private String name;

	private List<FixCommision> amount = new ArrayList<FixCommision>();

	private List<SingleItem> itemAssignment;

	private List<EmployeeAssignment> employeeAssignment;
	
	public Workshop(){}

	
	// na czas testu
	public void addCommision() {
		FixCommision fixCommsion1 = new FixCommision("Pierwszy serwis" ,true);
		amount.add(fixCommsion1);
		FixCommision fixCommsion2 = new FixCommision("Drugi serwis" ,true);
		amount.add(fixCommsion2);
		FixCommision fixCommsion3 = new FixCommision("Trzeci serwis" ,false);
		amount.add(fixCommsion3);
		System.out.println("Dodanie zakończone sukcesem");
	}
	
	// na czas testu
	public void removeServices() {
		amount.get(1).editProgress(false);
}
	
	public void removeServices(FixCommision SingleObject) {
		SingleObject.editProgress(false);
}

	public void checkCurrentTasks() {
		for (int i = 0; i < amount.size(); i++){
			if(amount.get(i).getInProgress()){
				System.out.println(amount.get(i).getName());
			}
		}

	}

	public void checkAllTasks() {
		for (int i = 0; i< amount.size(); i++){
			System.out.println(amount.get(i).getName());
		}
	}

	public void employeeAssignment() {

	}

	public void itemAssignment() {

	}

	
	
	// TEST
	public void addingCommisionsTest(){
		
		System.out.println();
		System.out.println("--TEST SERWISU--");
		addCommision();
		System.out.println("Sprawdzanie wszystkich zadan serwisu(nawet wykonanych)");
		checkAllTasks();
		System.out.println("Sprawdzanie aktualnych zadan serwisu");
		checkCurrentTasks();
		removeServices();
		System.out.println("Usuwanie zadanie(wykonane)");
		System.out.println("Sprawdzanie aktualnych zadan serwisu (po wykonaniu 1)");
		checkCurrentTasks();
	}
	
	
}
