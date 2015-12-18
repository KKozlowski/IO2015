package io.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.access.NetUser;
import io.crew.EmployeeAssignment;
import io.crew.SkillType;
import io.general.App;

public class Services {

	protected ArrayList<Service> services = new ArrayList<Service>();
	protected ArrayList<ServiceType> serviceTypes = new ArrayList<ServiceType>();

	public Service getServiceByID(int serviceID) {
		for(Service s : services)
			if(s.getServiceID() == serviceID)
				return s;
		return null;
	}

	public Collection<Service> listAllServices() {
		return services;
	}

	public Collection<Service> listServicesOfType(int typeID) {
		ArrayList<Service> result = new ArrayList<Service>();
		for(Service s : services)
			if(s.getTypeID() == typeID)
				result.add(s);
		return result;
	}

	public void deleteService(int serviceID) {
		for(Service s : services) {
			if(s.getServiceID() == serviceID) {
				services.remove(s);
				break;
			}
		}
	}

	public void assignEmployee(int serviceID, Date beginning, Date ending, List<SkillType> skills, String notes) {
		Service s = getServiceByID(serviceID);
		EmployeeAssignment ea = new EmployeeAssignment(beginning, ending, beginning == null && ending == null, skills, s, notes);
		s.getAssignments().add(ea);
	}
	public void assignEmployee(int serviceID, List<SkillType> skills, String notes) {
		assignEmployee(serviceID, null, null, skills, notes);
	}

	public void makeReservation(int serviceID, int userID) {
		Service s = getServiceByID(serviceID);
		List<NetUser> reservations = s.getReservations();
		if(s.getUsersLimit() == reservations.size())
			return; // TODO EXCETPION
		for(NetUser u : reservations)
			if(u.getID() == userID)
				return; // TODO EXCETPION
		reservations.add((NetUser) App.getInstance().getUsers().getUserByID(userID));
	}

	public void rentItem(int serviceID, int itemID) {

	}

	public ServiceType getServiceTypeByID(int serviceTypeID) {
		for(ServiceType st : serviceTypes)
			if(st.getTypeID() == serviceTypeID)
				return st;
		return null;
	}

	public void addService(Service service) {
		services.add(service);
	}

	public void deleteServiceType(int serviceTypeID) {
		for(ServiceType st : serviceTypes) {
			if(st.getTypeID() == serviceTypeID) {
				serviceTypes.remove(st);
				break;
			}
		}
	}

	public void addServiceType(ServiceType serviceType) {
		serviceTypes.add(serviceType);
	}
	
	public List<Service> getUsersReservations(int userID) {
		ArrayList<Service> result = new ArrayList<Service>();
		for(Service s : listAllServices())
			for(NetUser u : s.getReservations())
				if(u.getID() == userID)
					result.add(s);
		return result;
	}

}
