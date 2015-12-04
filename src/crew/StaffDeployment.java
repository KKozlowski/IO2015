package crew;

import java.util.Date;
import java.util.List;

import services.Service;

import java.util.Collection;

public class StaffDeployment {

	private Employee employee;

	public EmployeeAssignment createAssignment(Date beginDate, Date endDate, boolean timeIndependent, Service service) {
		return null;
	}

	public void assignEmployee(Employee employee, EmployeeAssignment assignment) {

	}

	public List<Employee> returnEmployees() {
		return null;
	}

	public Employee returnSpecifcEmployee(String pesel) {
		return null;
	}

	public List<EmployeeAssignment> returnEmployeeAssignments(String pesel) {
		return null;
	}

	public List<EmployeeAssignment> returnAssignments() {
		return null;
	}

}
