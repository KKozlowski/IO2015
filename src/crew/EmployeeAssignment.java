package crew;

import java.util.Date;
import java.util.List;
import services.Service;
import workshop.Workshop;

public class EmployeeAssignment {

	private Date beginning;

	private Date ending;

	private Boolean timeIndependent;

	private Employee employee;

	private List<SkillType> requiredSkills;

	private String notes;

	private int id;

	private Service service;

	private Workshop workshop;

	private SkillType[] skillType;

	private StaffDeployment staffDeployment;
	
	EmployeeAssignment(Date beginDate, Date endDate, boolean timeIndp, List<SkillType> reqSkills, Service service, String notes)
	{
		beginning = beginDate;
		endDate = ending;
		timeIndependent = timeIndp;
		requiredSkills = reqSkills;
		this.service = service;
		this.notes = notes;
	}

	public Date getBeginning() {
		
		return beginning;
	}

	public Date getEnding() {
		return ending;
	}

	public String getNote() {
		return notes;
	}

	public void addEmployee(Employee employee) {
		
		this.employee = employee;
	}

	public void addSkill(SkillType skill) {
		requiredSkills.add(skill);
	}

	public void setNote(String note) {
		notes = note;
	}

	public void removeSkill(int skillNr) {
		requiredSkills.remove(skillNr);
	}

	public void removeEmployee() {
		employee = null;
	}
	
	public Employee getEmployee(){
		
		return employee;
		
	}
	
	
	
	@Override
	public String toString(){
		
		if(employee instanceof Employee)
		{
			return "Assignment: Beginning date - " + beginning + " ending date: " + ending + " Required skills: " + requiredSkills + " Service type: " + service + "assigned employee " + employee;
		}
		else
		{
			return "Assignment: Beginning date - " + beginning + " ending date: " + ending + " Required skills: " + requiredSkills + " Service type: " + service + "Employee not yet assigned!";
		}
			
	}
	

}