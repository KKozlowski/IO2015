package crew;

import java.util.Date;
import java.util.List;

import services.Service;

import java.util.ArrayList;
import java.util.Collection;

public class StaffDeployment {

	private ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/**
	 * Róbcie z tym co chcecie.
	 * @return Newly added employee.
	 */
	public Employee addEmployee(){
		Employee e = new Employee();
		employees.add(e);
		return e;
	}
	
	public void removeEmployee(Employee toRemove){
		if (employees.contains(toRemove))
			employees.remove(toRemove);
	}
	
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
