package io.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.access.NetUser;
import io.crew.EmployeeAssignment;
import io.storage.ItemAssignment;

public class Service {

	public Service(int serviceID, int typeID, String name, Date begginingDate, Date endingDate,
			float price, int usersLimit) {
		super();
		this.serviceID = serviceID;
		this.typeID = typeID;
		this.name = name;
		this.endingDate = endingDate;
		this.price = price;
		this.begginingDate = begginingDate;
		this.usersLimit = usersLimit;
	}

	private int serviceID;

	private int typeID;

	private String name;
	
	private Date endingDate;

	private float price;

	private Date begginingDate;

	private int usersLimit;
	
	private List<NetUser> reservations = new ArrayList<NetUser>();
	
	private List<EmployeeAssignment> assignments = new ArrayList<EmployeeAssignment>();

	private List<ItemAssignment> rentEntries = new ArrayList<ItemAssignment>();

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getBegginingDate() {
		return begginingDate;
	}

	public void setBegginingDate(Date begginingDate) {
		this.begginingDate = begginingDate;
	}

	public int getUsersLimit() {
		return usersLimit;
	}

	public void setUsersLimit(int usersLimit) {
		this.usersLimit = usersLimit;
	}

	public List<EmployeeAssignment> getAssignments() {
		return assignments;
	}

	public List<ItemAssignment> getRentEntries() {
		return rentEntries;
	}

	public List<NetUser> getReservations() {
		return reservations;
	}

	@Override
	public String toString() {
		return "Service [serviceID=" + serviceID + ", typeID=" + typeID + ", name=" + name + ", endingDate="
				+ endingDate + ", price=" + price + ", begginingDate=" + begginingDate + ", usersLimit=" + usersLimit
				+ ", assignmentCount=" + assignments.size() + ", reservationCount=" + reservations.size() + "]";
	}

}
