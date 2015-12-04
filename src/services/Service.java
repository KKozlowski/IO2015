package services;

import java.util.Date;
import java.util.List;

import crew.EmployeeAssignment;
import storage.SingleItem;
import storage.ItemAssignment;

public class Service {

	private Date beginning;

	private Date ending;

	private int serviceID;

	private int typeID;

	private Date endingDate;

	private float price;

	private Date begginingDate;

	private int usersLimit;

	private List<EmployeeAssignment> assignment;

	private List<ItemAssignment> rentEntry;

}
