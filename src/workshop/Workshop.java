package workshop;

import java.util.ArrayList;
import java.util.List;

import storage.ItemAssignment;
import storage.SingleItem;
import crew.EmployeeAssignment;

public class Workshop {

	private String name;

	private List<FixCommision> amount = new ArrayList<FixCommision>();

	private List<SingleItem> itemAssignment;

	private List<EmployeeAssignment> employeeAssignment;

	
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
			if(amount.get(i).getProgress()){
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

}
