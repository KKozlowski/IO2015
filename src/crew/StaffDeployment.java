package crew;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import services.Service;

import java.util.ArrayList;
import java.util.Collection;

public class StaffDeployment {

	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<EmployeeAssignment> assignments = new ArrayList<EmployeeAssignment>();
	private ArrayList<SkillType> possibleSkills = new ArrayList<SkillType>();
	
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
		
		assignment.addEmployee(employee);

	}

	public List<Employee> returnEmployees() {
		
		return employees;
	}

	public Employee returnSpecifcEmployee(String pesel) {

		Employee specificEmployee = employees.stream().filter(e -> e.getPesel().equalsIgnoreCase(pesel)).findFirst().get();
		return specificEmployee;
		
	}

	public List<EmployeeAssignment> returnEmployeeAssignments(String pesel) {

		List<EmployeeAssignment> employeeAssignments = new ArrayList<EmployeeAssignment>();
		
		for(int i=0; i < assignments.size(); i++)
		{
			if(assignments.get(i).getEmployee().getPesel().equalsIgnoreCase(pesel))
			{
				employeeAssignments.add(assignments.get(i));
			}
		}
		
		return employeeAssignments;
	}

	public List<EmployeeAssignment> returnAssignments() {
		
		return assignments;
		
	}

}
