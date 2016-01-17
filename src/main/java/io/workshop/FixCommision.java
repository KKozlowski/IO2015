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

	
	public Boolean getInProgress() {
		return inProgress;
	}

	public void setInProgress(Boolean inProgress) {
		this.inProgress = inProgress;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Date getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(Date dateEnded) {
		this.dateEnded = dateEnded;
	}

	public SingleItem getItem() {
		return item;
	}

	public void setItem(SingleItem item) {
		this.item = item;
	}

	public NewItem getNewItem() {
		return newItem;
	}

	public void setNewItem(NewItem newItem) {
		this.newItem = newItem;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// na czas testu
	public FixCommision(String name, boolean inProgress){
		this.name = name;
		this.inProgress = inProgress;
	}
	
	
	public void editProgress(boolean progress) {
		inProgress = progress;
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
