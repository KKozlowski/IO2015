package io.crew;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "SkillTypes")
public class SkillType {

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Name;
	
	public SkillType(int id,String Name){
		this.id=id;
		this.Name=Name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "SkillType id=" + id + ", Name=" + Name;
	}
	
}
