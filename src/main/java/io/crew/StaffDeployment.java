package io.crew;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import io.access.InnerUser;
import io.access.PermissionType;
import io.general.App;
import io.services.Service;

import java.util.ArrayList;
import java.util.Collection;

public class StaffDeployment {

	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<EmployeeAssignment> assignments = new ArrayList<EmployeeAssignment>();
	private ArrayList<SkillType> possibleSkills = new ArrayList<SkillType>();
	private boolean timeIndependant;
	
	/**
	 * R�bcie z tym co chcecie.
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
	
	public void createAssignment(Date beginDate, Date endDate, boolean timeIndependent,List<SkillType> reqSkills, Service service, String notes) {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			EmployeeAssignment assignment = new EmployeeAssignment(beginDate, endDate, timeIndependant, reqSkills, service, notes);
			assignments.add(assignment);
		}
		
	}

	public void assignEmployee(Employee employee, EmployeeAssignment assignment) {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			assignment.addEmployee(employee);
		}

	}

	public List<Employee> returnEmployees() {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			return employees;
		}
		
		return null;
	}

	public Employee returnSpecifcEmployee(String pesel) {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			Employee specificEmployee = employees.stream().filter(new Predicate<Employee>() {
				@Override
				public boolean test(Employee e) {
					return e.getPesel().equalsIgnoreCase(pesel);
				}
			}).findFirst().get();
			return specificEmployee;
		}
		else if(App.getInstance().getUsers().getCurrentUser() instanceof InnerUser)
		{
			((InnerUser) App.getInstance().getUsers().getCurrentUser()).getEmploymentInfo();

		}
		
		return null;
		
	}

	public List<EmployeeAssignment> returnEmployeeAssignments(String pesel) {

		List<EmployeeAssignment> employeeAssignments = new ArrayList<EmployeeAssignment>();
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			for(int i=0; i < assignments.size(); i++)
			{
				if(assignments.get(i).getEmployee().getPesel().equalsIgnoreCase(pesel))
				{
					employeeAssignments.add(assignments.get(i));
				}
			}
		}
		else if(App.getInstance().getUsers().getCurrentUser() instanceof InnerUser)
		{
			for(int i=0; i < assignments.size(); i++)
			{
				if(assignments.get(i).getEmployee().equals(((InnerUser) App.getInstance().getUsers().getCurrentUser()).getEmploymentInfo()))
				{
					employeeAssignments.add(assignments.get(i));
				}
			}
		}
		
		return employeeAssignments;
	}

	public List<EmployeeAssignment> returnAssignments() {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			return assignments;
		}
		else
		{
			return null;
		}
	}
	
	public void removeAssignment(int nr)
	{
		assignments.remove(nr);
	}

}
