package io.services.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.access.PermissionType;
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
	public String getByID(@RequestParam(value="id") int id) {
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
}
