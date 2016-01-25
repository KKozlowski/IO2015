package io.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.access.models.UserEntity;

@Entity
@Table(name = "services")
public class Service {

	public Service() {}
	
	public Service(ServiceType serviceType, String name, Date begginingDate, Date endingDate,
			float price, int usersLimit) {
		super();
		this.serviceType = serviceType;
		this.name = name;
		this.endingDate = endingDate;
		this.price = price;
		this.begginingDate = begginingDate;
		this.usersLimit = usersLimit;
		this.ticketClass = "";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private ServiceType serviceType;

	private String name;
	
	private Date endingDate;

	private float price;

	private Date begginingDate;

	private int usersLimit;
	
	private int cruiseId;

	private String ticketClass;

	private int roomID;
	
	@ManyToMany
	private List<UserEntity> reservations = new ArrayList<UserEntity>();
	
//	@ManyToMany
//	private List<EmployeeAssignment> assignments = new ArrayList<EmployeeAssignment>();

//	@ManyToMany
//	private List<ItemAssignment> rentEntries = new ArrayList<ItemAssignment>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
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

	public int getCruiseId() {
		return cruiseId;
	}

	public void setCruiseId(int cruiseId) {
		this.cruiseId = cruiseId;
	}

	public String getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

//	public List<EmployeeAssignment> getAssignments() {
//		return assignments;
//	}

//	public List<ItemAssignment> getRentEntries() {
//		return rentEntries;
//	}

	public List<UserEntity> getReservations() {
		return reservations;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", serviceType=" + serviceType + ", name=" + name + ", endingDate=" + endingDate
				+ ", price=" + price + ", begginingDate=" + begginingDate + ", usersLimit=" + usersLimit + ", cruiseId="
				+ cruiseId + ", ticketClass=" + ticketClass + ", roomID=" + roomID + ", reservations=" + reservations
				+ "]";
	}
}
