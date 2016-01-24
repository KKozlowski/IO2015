package io.services.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Transactional
public class ServicesController {

	@Autowired
	public ServiceDao serviceDao;
	
	@Autowired
	public ServiceTypeDao serviceTypeDao;
	
	private final String NO_PERMISSIONS = "YOU DON'T HAVE MATCHING PERMISSIONS";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@RequestMapping(value = "/hello")
	@ResponseBody
	public String workshop(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
			return "Witaj w module usług"
					+"<br><br><a href='/services/insert'>Dodaj usługę</a>"
					+"<br><a href='/services/deleteService'>Usuń usługę</a>"
					+"<br><a href='/services/updateService'>Modyfikuj usługę</a>"
					+"<br><br><a href='/services/insertType'>Dodaj typ usługi</a>"
					+"<br><a href='/services/deleteServiceType'>Usuń typ usługi</a>"
					+"<br><a href='/services/updateServiceType'>Modyfikuj typ usługi</a>"
					+"<br><br><a href='/services/getById'>Znajdź usługę po ID</a>"
					+"<br><a href='/services/getTypeById'>Znajdź typ usługi po ID</a>"
					+"<br><a href='/services/listAllServices'>Znajdz wszystkie usługi</a>"
					+"<br><a href='/services/listAllServiceTypes'>Znajdź wszystkie typy usług</a>"
					+"<br><br><a href='/services/makeReservation'>Dokonaj rezerwacji</a>";		
		else 
			 return NO_PERMISSIONS;
	}

	@RequestMapping("/listAllServices")
	public String listAllServices() {
		List<Service> list = serviceDao.findAll();
		if (!list.isEmpty() && list.size() > 0) {
			String result = "";
			for(Service s : list)
				result += (result.length() != 0 ? "\n" : "") + s.getName();
			return result;
		} else {
			return "Brak usług/rejsów";
		}
	}
	
	@RequestMapping("/getById")
	public String getById(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Znajdź usługę po ID:</p>"
				+"<form id='frm1' action='/services/getByIdSend' method='POST'>"
				+"<br /><br>ID: <input type='text' name='id'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/getByIdSend")
	public String getByIdSend(int id) {
		Service service = serviceDao.getById(id);
		if (service != null) {
			return service.getName();
		} else {
			return "Nie znaleziono usługi o podanym ID";
		}
	}
	
	@RequestMapping("/getTypeById")
	public String getTypeById(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Znajdź usługę po ID:</p>"
				+"<form id='frm1' action='/services/getTypeByIdSend' method='POST'>"
				+"<br /><br>ID: <input type='text' name='id'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/getTypeByIdSend")
	public String getTypeByIdSend(int id) {
		ServiceType serviceType = serviceTypeDao.getById(id);
		if (serviceType != null) {
			return serviceType.getName();
		} else {
			return "Nie znaleziono usługi o podanym ID";
		}
	}
	
	@RequestMapping(value = "/deleteService")
	public String deleteService(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Usuń usługę o ID:</p>"
				+"<form id='frm1' action='/services/deleteServiceSend' method='POST'>"
				+"<br /><br>ID: <input type='text' name='id'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/deleteServiceSend")
	public boolean deleteServiceSend(@RequestParam(value="id") int id, HttpSession h) {
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
	
	@RequestMapping(value = "/deleteServiceType")
	public String deleteServiceType(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Usuń typ usługi o ID:</p>"
				+"<form id='frm1' action='/services/deleteServiceTypeSend' method='POST'>"
				+"<br /><br>ID: <input type='text' name='id'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/deleteServiceTypeSend")
	public boolean deleteServiceTypeSend(@RequestParam(value="id") int id, HttpSession h) {
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
	
	@RequestMapping("/listAllServiceTypes")
	public String listAllServiceTypes(HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			List<ServiceType> list = serviceTypeDao.findAll();
			String result = "";
			if (!list.isEmpty() && list.size() > 0) {
				for(ServiceType s : list)
					result += (result.length() != 0 ? "\n" : "") + s.getName();
				return result;
			} else {
				return "Brak typów usług/rejsów";
			}
		} else {
			return NO_PERMISSIONS;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Dodawanie usługi:</p>"
				+"<form id='frm1' action='/services/insertSend' method='POST'>"
				+"<br /><br>ID typu usługi: <input type='text' name='serviceType'/>"
				+"<br />Nazwa: <input type='text' name='name'/>"
				+"<br />Data rozpoczęcia: <input type='text' name='begginingDate'/>"
				+"<br />Data Rozpoczoczecia: <input type='text' name='endingDate'/>"
				+"<br />Cena: <input type='text' name='price'/>"
				+"<br />Limit osób: <input type='text' name='usersLimit'/><br />"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}

	@RequestMapping("/insertSend")
	public String insertSend(int serviceType, String name, String begginingDate, String endingDate,
			float price, int usersLimit, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			try {
				Date start = sdf.parse(begginingDate);
				Date end = sdf.parse(endingDate);
				if (start.after(end)) {
					return "Początek usługi musi być wcześniej niż koniec!";
				}
				ServiceType sType = serviceTypeDao.getById(serviceType);
				if (sType == null) {
					return "Złe ID typu usługi!";
				}
				Service service = new Service(sType, name, start, end, price,  usersLimit);
				serviceDao.create(service);
				return "OK";
			} catch (Exception e) {
				e.printStackTrace();
				return "ERROR";
			}
		} else {
			return NO_PERMISSIONS;
		}
	}
	
	@RequestMapping("/insertType")
	public String insertType(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Dodawanie typu usługi:</p>"
				+"<form id='frm1' action='/services/insertTypeSend' method='POST'>"
				+"<br /><br>Nazwa: <input type='text' name='name'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;	
	}
	
	@RequestMapping("/insertTypeSend")
	public String insertTypeSend(String name, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			ServiceType serviceType = new ServiceType(name);
			serviceTypeDao.create(serviceType);
			return "OK";
		} else {
			return NO_PERMISSIONS;
		}
	}
	
	@RequestMapping("/makeReservation")
	public String makeReservation(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Rezerwacja:</p>"
				+"<form id='frm1' action='/services/makeReservationSend' method='POST'>"
				+"<br /><br>ID usługi: <input type='text' name='serviceID'/><br />"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}

	@RequestMapping("/makeReservationSend")
	public String makeReservationSend(HttpSession h, int serviceID) {
		if(App.getInstance().getUsers().isUserLogged(h.getId())) {
			Service service = serviceDao.getById(serviceID);
			service.getReservations().add(new UserEntity(App.getInstance().getUsers().getUserBySessionID(h.getId()).getID()));
			serviceDao.update(service);
			return "OK";
		} else {
			return NO_PERMISSIONS;
		}
	}
	
	@RequestMapping("/updateService")
	public String updateService(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Modyfikacja usługi:</p>"
				+"<form id='frm1' action='/services/updateServiceForm' method='POST'>"
				+"<br /><br>ID: <input type='text' name='id'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/updateServiceForm")
	public String updateServiceForm(int id, HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			Service service = serviceDao.getById(id);
			if (service == null) {
				return "Złe ID!";
			}
			return "<!DOCTYPE html><html><body><p>Modyfikacja usługi:</p>"
					+"<form id='frm1' action='/services/updateServiceSend' method='POST'>"
					+"<input type='hidden' name='serviceId' value='" + id + "'/>"
					+"<br /><br>ID typu usługi: <input type='text' name='serviceTypeId' value='" + service.getServiceType().getId() + "'/>"
					+"<br />Nazwa: <input type='text' name='name' value='" + service.getName() + "'/>"
					+"<br />Data Rozpoczoczecia: <input type='text' name='beginningDate' value='" + sdf.format(service.getBegginingDate()) + "'/>"
					+"<br />Data Zakonczenia: <input type='text' name='endingDate' value='" + sdf.format(service.getEndingDate()) + "'/>"
					+"<br />Cena: <input type='text' name='price' value='" + service.getPrice() + "'/>"
					+"<br />Limit osób: <input type='text' name='usersLimit' value='" + service.getUsersLimit() + "'/>"
					+"<br />ID rejsu: <input type='text' name='cruiseId' value='" + service.getCruiseId() + "'/>"
					+"<br />Klasa biletu: <input type='text' name='ticketClass' value='" + service.getTicketClass() + "'/>"
					+"<br />ID pokoju: <input type='text' name='roomId' value='" + service.getRoomID() + "'><br />"
					+"<br /><button type='submit'>OK</button></form>"
					+"</body></html>";
		} else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/updateServiceSend")
	public String updateServiceSend(int serviceId, int serviceTypeId, String name, String beginningDate, String endingDate,
			float price, int usersLimit, int cruiseId, String ticketClass, int roomId, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			try {
				Service service = serviceDao.getById(serviceId);
				ServiceType sType = serviceTypeDao.getById(serviceTypeId);
				if (sType == null) {
					return "Złe ID typu usługi!";
				}
				service.setServiceType(sType);
				service.setName(name);
				service.setBegginingDate(sdf.parse(beginningDate));
				service.setEndingDate(sdf.parse(endingDate));
				service.setPrice(price);
				service.setUsersLimit(usersLimit);
				service.setCruiseId(cruiseId);
				service.setTicketClass(ticketClass);
				service.setRoomID(roomId);
				serviceDao.update(service);
				return "OK";
			} catch (Exception e) {
				return "ERROR";
			}
		} else {
			return NO_PERMISSIONS;
		}
	}
	
	@RequestMapping("/updateServiceType")
	public String updateServiceType(HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan))
		return "<!DOCTYPE html><html><body><p>Modyfikacja typu usługi:</p>"
				+"<form id='frm1' action='/services/updateServiceTypeForm' method='POST'>"
				+"<br /><br>ID: <input type='text' name='id'/>"
				+"<br /><button type='submit'>OK</button></form>"
				+"</body></html>";
		else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/updateServiceTypeForm")
	public String updateServiceTypeForm(int id, HttpSession h){
		if (App.getInstance().getUsers().isCurrentUserAdmin(h.getId()) 
				   || App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			ServiceType serviceType = serviceTypeDao.getById(id);
			if (serviceType == null) {
				return "Złe ID!";
			}
			return "<!DOCTYPE html><html><body><p>Modyfikacja usługi:</p>"
					+"<form id='frm1' action='/services/updateServiceTypeSend' method='POST'>"
					+"<input type='hidden' name='id' value='" + id + "'/>"
					+"<br />Nazwa: <input type='text' name='name' value='" + serviceType.getName() + "'/>"
					+"<br /><button type='submit'>OK</button></form>"
					+"</body></html>";
		} else
			return NO_PERMISSIONS;
	}
	
	@RequestMapping("/updateServiceTypeSend")
	public String updateServiceTypeSend(int id, String name, HttpSession h) {
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(h.getId(), PermissionType.serviceMan)) {
			ServiceType serviceType = serviceTypeDao.getById(id);
			serviceType.setName(name);
			serviceTypeDao.update(serviceType);
			return "OK";
		} else {
			return NO_PERMISSIONS;
		}
	}
}
