package io.services.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.services.Service;
import io.services.models.ServiceDao;

@RestController
@RequestMapping("/services")
public class ServicesController {

	@Autowired
	public ServiceDao serviceDao;

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
}
