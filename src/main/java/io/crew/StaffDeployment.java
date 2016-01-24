package io.crew;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import io.access.InnerUser;
import io.access.PermissionType;
import io.crew.exceptions.DuplicateCertificateException;
import io.crew.exceptions.DuplicateSkillException;
import io.crew.exceptions.UnassignableEmployeeException;
import io.general.App;
import io.services.Service;

import java.util.ArrayList;
import java.util.Collection;

public class StaffDeployment {

	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<EmployeeAssignment> assignments = new ArrayList<EmployeeAssignment>();
	private ArrayList<SkillType> possibleSkills = new ArrayList<SkillType>();
	private ArrayList<Certificate> certificates = new ArrayList<Certificate>();
	
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
			EmployeeAssignment assignment = new EmployeeAssignment(beginDate, endDate, timeIndependent, reqSkills, service, notes);
			assignments.add(assignment);
		}
		
	}
	
	public boolean isEmployeeAssignable(Employee employee, EmployeeAssignment assignment)
	{
		List<SkillType> skills = assignment.getReqSkills();
		List<Certificate> employeeCert= employee.getCertificates();
		List<SkillType> certSkills;
		List<Boolean> skillCheck = new ArrayList<Boolean>();
		
		for(int z=0; z<skills.size(); z++)
		{
			skillCheck.add(false);
		}
		
		
		for(int i=0;i<skills.size();i++)
		{
			for(int j=0; j<employeeCert.size();j++)
			{
				certSkills = employeeCert.get(j).getSkills();
				for(int k=0; k<certSkills.size();k++)
				{
					if(certSkills.get(k).getId()==skills.get(i).getId())
					{
						skillCheck.set(i, true);
						break;
					}
				}
			}
		}
		
		return skillCheck.contains(false);
	}
	
	public void assignEmployee(Employee employee, EmployeeAssignment assignment) throws UnassignableEmployeeException {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			
			if(isEmployeeAssignable(employee, assignment))
			{
				throw new UnassignableEmployeeException();
			}
			else
			{
				assignment.addEmployee(employee);
			}
				
		}

	}
	
	public void addCertToEmployee(int index, Certificate cert) throws DuplicateCertificateException
	{
		List<Certificate> certs = employees.get(index).getCertificates();
		if(certs.contains(cert))
		{
			throw new DuplicateCertificateException();
		}
		else
		{
			employees.get(index).addCertificate(cert);
		}
		
	}
	
	public void addSkill(SkillType skill) throws DuplicateSkillException
	{
		for(int i=0; i<possibleSkills.size();i++)
		{
			if(possibleSkills.get(i).getName().equalsIgnoreCase(skill.getName()))
			{
				throw new DuplicateSkillException();
			}
		}
		
		possibleSkills.add(skill);
	}

	public List<Employee> returnEmployees() {
		
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			return employees;
		}
		
		return null;
	}

	public Employee returnSpecifcEmployee(final String pesel) {
		
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
		if(App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.crewMaster))
		{
			assignments.remove(nr);
		}	
	}



	public ArrayList<Certificate> getCertificates() {
		return certificates;
	}



	public void setCertificates(ArrayList<Certificate> certificates) {
		this.certificates = certificates;
	}
}
