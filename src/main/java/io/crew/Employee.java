package io.crew;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.access.InnerUser;
import io.access.PersonalData;
import io.access.User;
//@Entity
//@Table(name="employees")
public class Employee {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Date employmentDate;
	
	//@OneToOne
	private InnerUser userAccount;
	
	//@ManyToMany
	public List<Certificate> certificates;

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
