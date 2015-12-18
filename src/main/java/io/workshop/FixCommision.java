package io.workshop;

import java.util.Date;

import io.storage.SingleItem;

public class FixCommision {

	private String name;

	private Boolean inProgress;

	private String additionalInfo;

	private Date dateStarted;

	private Date dateEnded;

	private SingleItem item;
	
	private NewItem newItem; 

	
	// na czas testu
	public FixCommision(String name, boolean inProgress){
		this.name = name;
		this.inProgress = inProgress;
	}
	
	public String getName(){
		return name;
	}

	public void editProgress(boolean progress) {
		inProgress = progress;
	}
	public boolean getProgress(){
		return inProgress;
	}

	public void editInfo(String info) {
		additionalInfo = info;
	}

	public void editDateEnded(Date endDate) {
		dateEnded = endDate;
	}
	
	public void itemAssignment(SingleItem assignedItem) {
		item = assignedItem;
	}

	public void addNewItem() {
		//konstruktor bedzie parametrowy, ale to z interfejsem
		NewItem newItem = new NewItem();
		
	}
	
	public void addNewItem(String name, int nrID, Date serviceDate, String client, String additionalInfo) {
		//na czas testu
		NewItem newItem = new NewItem(name, nrID, serviceDate, client, additionalInfo);
		
	}
	
	

}
