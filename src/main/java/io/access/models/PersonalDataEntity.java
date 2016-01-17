package io.access.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "personaldata")
public class PersonalDataEntity {

	@Id
	protected int userID;
	
	
	protected String name;

	
	protected String surname;

	
	protected String pesel;

	
	protected String adress;

	
	protected String idCardID;

	
	protected String mail;
	
	
	public PersonalDataEntity() { }
	
	public PersonalDataEntity( String name, String surname, String pesel, String adress, String idCardID, String mail) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.adress = adress;
		this.idCardID = idCardID;
		this.mail = mail;
	}
	
	public void updataData(String name, String surname, String email, String idid, String pesel, String adres){
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.adress = adres;
		this.idCardID = idid;
		this.mail = email;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPESEL() {
		return pesel;
	}

	public String getAdress() {
		return adress;
	}

	public String getCardID() {
		return idCardID;
	}

	public String getMail() {
		return mail;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	public void setName(String data) {
		this.name = data;
	}

	public void setSurname(String data) {
		this.surname = data;
	}

	public void setPESEL(String data) {
		this.pesel = data;
	}

	public void setAdress(String data) {
		this.adress = data;
	}

	public void setCardID(String data) {
		this.idCardID = data;
	}

	public void setMail(String data) {
		this.mail = data;
	}
}
