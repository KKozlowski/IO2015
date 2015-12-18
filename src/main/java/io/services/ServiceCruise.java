package io.services;

import java.util.Date;

public class ServiceCruise extends Service {

	public ServiceCruise(int serviceID, int typeID, String name, Date begginingDate, Date endingDate, float price,
			int usersLimit) {
		super(serviceID, typeID, name, begginingDate, endingDate, price, usersLimit);
		// TODO Auto-generated constructor stub
	}

	private int cruise_id;

	private String ticketClass;

	private int roomID;

}
