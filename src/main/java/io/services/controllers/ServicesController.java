package io.services.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.access.PermissionType;
import io.access.models.UserEntity;
import io.general.App;
import io.services.Service;
import io.services.ServiceType;
import io.services.models.ServiceDao;
import io.services.models.ServiceTypeDao;

@RestController
@RequestMapping("/services")
public class ServicesController {

	@Autowired
	public ServiceDao serviceDao;
	
	@Autowired
	public ServiceTypeDao serviceTypeDao;

	@RequestMapping("/list-all-services")
	public String listAllServices() {
		List<Service> list = serviceDao.findAll();
		String result = "";
		for(Service s : list)
			result += (result.length() != 0 ? "\n" : "") + s.getName();
		return result;
	}
	
	@RequestMapping("/get-by-id")
	public String getByID(int id) {
		Service service = serviceDao.getById(id);
		return service.getName();
	}
	
	@RequestMapping("/delete-service")
	public boolean deleteService(@RequestParam(value="id") int id, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			try {
		    	serviceDao.delete(serviceDao.getById(id));
		    	return true;
		    }
		    catch (Exception ex) {
		      return false;
		    }
		} else {
			return false;
		}
	}
	
	@RequestMapping("/delete-service-type")
	public boolean deleteServiceType(@RequestParam(value="id") int id, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			try {
		    	serviceTypeDao.delete(serviceTypeDao.getById(id));
		    	return true;
		    }
		    catch (Exception ex) {
		      return false;
		    }
		} else {
			return false;
		}
	}
	
	@RequestMapping("/list-all-service-types")
	public String listAllServiceTypes(HttpSession h) {
		String result = "";
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			List<ServiceType> list = serviceTypeDao.findAll();
			for(ServiceType s : list)
				result += (result.length() != 0 ? "\n" : "") + s.getName();
			return result;
		} else {
			return result;
		}
	}

	@RequestMapping("/insert")
	public String insert(int serviceType, String name, int begginingDate, int endingDate,
			float price, int usersLimit, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			Service service = new Service(serviceType, name, new Date(begginingDate), new Date(endingDate), price,  usersLimit);
			serviceDao.create(service);
			return "OK";
		} else {
			return "";
		}
	}
	
	@RequestMapping("/insert-type")
	public String insertType(String name, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			ServiceType serviceType = new ServiceType(name);
			serviceTypeDao.create(serviceType);
			return "OK";
		} else {
			return "";
		}
	}

	@RequestMapping("/make-reservation")
	public String makeReservation(HttpSession h, @RequestParam(value="serviceid") int serviceID) {
		if(App.getInstance().getUsers().isUserLogged(h.getId())) {
			Service service = serviceDao.getById(serviceID);
			service.getReservations().add(new UserEntity(App.getInstance().getUsers().getUserBySessionID(h.getId()).getID()));
			serviceDao.update(service);
			return "OK";
		}
		return "";
	}
	
	@RequestMapping("/update-service")
	public String updateService(int serviceId, int serviceTypeId, String name, int beginningDate, int endingDate,
			float price, int usersLimit, int cruiseId, String ticketClass, int roomId, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			Service service = serviceDao.getById(serviceId);
			service.setServiceType(serviceTypeDao.getById(serviceTypeId));
			service.setName(name);
			service.setBegginingDate(new Date(beginningDate));
			service.setEndingDate(new Date(endingDate));
			service.setPrice(price);
			service.setUsersLimit(usersLimit);
			service.setCruiseId(cruiseId);
			service.setTicketClass(ticketClass);
			service.setRoomID(roomId);
			serviceDao.update(service);
			return "OK";
		} else {
			return "";
		}
	}
	
	@RequestMapping("/update-service-type")
	public String updateServiceType(int id, String name, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			ServiceType serviceType = serviceTypeDao.getById(id);
			serviceType.setName(name);
			serviceTypeDao.update(serviceType);
			return "OK";
		} else {
			return "";
		}
	}
}
