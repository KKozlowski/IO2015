package services;

import java.util.ArrayList;
import java.util.Collection;

public class ServicesCruise extends Services {
	
	public Collection<ServiceCruise> listCruiseServicesOfType(int typeID) {
		Collection<ServiceCruise> result = new ArrayList<ServiceCruise>();
		for(Service s : services)
			if(s instanceof ServiceCruise && s.getTypeID() == typeID)
				result.add((ServiceCruise) s);
		return result;
	}

	public ServiceCruise getServiceByID(int serviceID) {
		Service s = super.getServiceByID(serviceID);
		return s instanceof ServiceCruise ? (ServiceCruise) s : null;
	}

	public void makeReservation(int serviceID, int userID, String ticketClass, int roomID) {
		super.makeReservation(serviceID, userID);
	}

	public void createService(ServiceCruise serviceCruise) {
		services.add(serviceCruise);
	}
}
