package io.services.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping("/insert")
	public String insert(int serviceType, String name, int begginingDate, int endingDate,
			float price, int usersLimit) {
		Service service = new Service(serviceType, name, new Date(begginingDate), new Date(endingDate), price,  usersLimit);
		serviceDao.create(service);
		return "ok";
	}
	
	@RequestMapping("/insert-type")
	public String insertType(String name) {
		ServiceType serviceType = new ServiceType(name);
		serviceTypeDao.create(serviceType);
		return "ok";
	}
}
