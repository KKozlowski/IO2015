package crew;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import access.InnerUser;
import access.PersonalData;
import access.User;

public class Employee {

	private Date employmentDate;
	private int employeeID;
	private InnerUser userAccount;
	private List<Certificate> certificates;

	public Employee(){
		//domyœlnie ustawione na czas obecny
		employmentDate=new Date();
		employeeID=0;
		userAccount=null;
		certificates=new ArrayList<Certificate>();
	}
	public Employee(int id, InnerUser userAcc){
		//domyœlnie ustawione na czas obecny
		employmentDate=new Date();
		employeeID=id;
		userAccount=userAcc;
		certificates=new ArrayList<Certificate>();
	}
	public Employee(Date date, int id, InnerUser userAcc){
		employmentDate=date;
		employeeID=id;
		userAccount=userAcc;
		certificates=new ArrayList<Certificate>();
	}
	public Date getEmploymentDate() {
		return employmentDate;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public List<Certificate> getCertificates() {
		return certificates;
	}
	public List<SkillType> getAllSkills() {
		List<SkillType> skills = new ArrayList<SkillType>();
		for(Certificate cert : certificates)
		{
			for(SkillType skill : cert.getSkills())
			{
				Boolean isUnique = true;
				for(SkillType egsistingType : skills)
				{
					if(egsistingType==skill)
					{
						isUnique = false;
						break;
					}
				}
				if(isUnique)
				{
					skills.add(skill);
				}
			}
		}
		return skills;
	}

	public int getID() {
		return employeeID;
	}
	
	public User getUserAccount(){
		return userAccount;
	}
	
	public void setUserAccount(InnerUser u){
		userAccount = u;
	}

	public List<EmployeeAssignment> getAssingments() {
		//TODO metoda zwracaj¹ca assingmenty pracownika
		return null;
	}

	public PersonalData getPersonalData() {
		return userAccount.getPersonalData();
	}
	public String getPesel()
	{
		return userAccount.getPersonalData().getPESEL();
	}
	
	public void addCertificate(Certificate cert){
		//TODO do wykonania zmiana zale¿na od wykonania reszty
		certificates.add(cert);
	}
	public void removeCertificate(int index){
		certificates.remove(index);
	}
}
