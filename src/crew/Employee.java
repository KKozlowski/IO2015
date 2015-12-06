package crew;

import java.util.Date;
import java.util.List;

import access.PersonalData;
import access.User;

import java.util.Collection;

public class Employee {

	private Date employmentDate;

	private int employeeID;

	private User userAccount;

	private EmployeeAssignment employeeAssignment;

	private Certificate certificate;

	public Collection<SkillType> getAllSkills() {
		return null;
	}

	public int getID() {
		return 0;
	}
	
	public User getUserAccount(){
		return userAccount;
	}
	
	public void setUserAccount(User u){
		userAccount = u;
	}

	public List<EmployeeAssignment> getAssingments() {
		return null;
	}

	public PersonalData getPersonalData() {
		return userAccount.getPersonalData();
	}
	
	public String getPesel()
	{
		return null;
		
	}
	
}
