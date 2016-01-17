package io.workshop;

import java.util.Date;


public class NewItem {
	
	private int nrID;
	
	private String name;	

	private Date serviceDate;

	private String client;

	private String additionalInfo;
	
	public NewItem(){}
	
	public NewItem(String name, int nrID, Date serviceDate, String client, String additionalInfo){
		this.name = name;
		this.nrID = nrID;
		this.serviceDate = serviceDate;
		this.client = client;
		this.additionalInfo = additionalInfo;
		
	}
	
	public int getNrID() {
		return nrID;
	}

	public void setNrID(int nrID) {
		this.nrID = nrID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
