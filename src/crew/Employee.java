package crew;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import access.InnerUser;
import access.PersonalData;
import access.User;

public class Employee {

	private Date employmentDate;
	private InnerUser userAccount;
	private List<Certificate> certificates;

	public Employee(){
		//domyślnie ustawione na czas obecny
		employmentDate=new Date();
		userAccount=null;
		certificates=new ArrayList<Certificate>();
	}
	public Employee(InnerUser userAcc){
		//domyślnie ustawione na czas obecny
		employmentDate=new Date();
		userAccount=userAcc;
		userAccount.setEmploymentInfo(this);
		certificates=new ArrayList<Certificate>();
	}
	public Employee(Date date, InnerUser userAcc){
		employmentDate=date;
		userAccount=userAcc;
		userAccount.setEmploymentInfo(this);
		certificates=new ArrayList<Certificate>();
	}
	public Date getEmploymentDate() {
		return employmentDate;
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
	public User getUserAccount(){
		return userAccount;
	}

	public void setUserAccount(InnerUser u){
		userAccount = u;
	}

	public List<EmployeeAssignment> getAssingments() {
		//TODO metoda zwracająca assingmenty pracownika
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
		//TODO do wykonania zmiana zależna od wykonania reszty
		certificates.add(cert);
	}
	public void removeCertificate(int index){
		certificates.remove(index);
	}
	@Override
	public String toString() {
		return "Employee employmentDate=" + employmentDate + ", userAccount="
				+ userAccount + ", certificates=" + certificates;
	}
}
