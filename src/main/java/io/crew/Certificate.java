package io.crew;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.crew.exceptions.DuplicateSkillException;

import java.util.Date;

//@Entity
//@Table(name="certyficates")
public class Certificate {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int certificateID;
	private String name;
	//@ManyToMany
	private List<SkillType> skills;
	private Date expirationDate;
	
	
	public Certificate(){
		certificateID=0;
		name=null;
		skills = new ArrayList<SkillType>();
		expirationDate = null;
	}
	public Certificate(int ID, String designation){
		certificateID=ID;
		name=designation;
		skills = new ArrayList<SkillType>();
		expirationDate = null;
	}
	
	public int getCertificateID() {
		return certificateID;
	}
	public void setCertificateID(int certificateID) {
		this.certificateID = certificateID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SkillType> getSkills() {
		return skills;
	}
	public void addSkill(SkillType skill) throws DuplicateSkillException{
		
		for(int i=0;i<skills.size();i++)
		{
			if(skills.get(i).getName().equalsIgnoreCase(skill.getName()))
			{
				throw new DuplicateSkillException();
			}
		}
		
		this.skills.add(skill);
	}
	
	public void removeSkill(int index) {
		this.skills.remove(index);
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Override
	public String toString() {
		return "Certificate certificateID=" + certificateID + ", name=" + name
				+ ", skills=" + skills + ", expirationDate=" + expirationDate;
	}
}
