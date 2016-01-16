package io.access;

import io.general.App;
import io.models.PersonalDataEntity;

public class PersonalData {

	protected PersonalDataEntity entity;
	
	public PersonalDataEntity getEntity() {
		return entity;
	}

	public void setEntity(PersonalDataEntity entity) {
		this.entity = entity;
	}

	public PersonalData(String name, String surname, String pesel, String adress, String idCardID, String mail) {
		entity = new PersonalDataEntity(name, surname, pesel, adress, idCardID, mail);
	}
	
	public PersonalData(PersonalDataEntity pde) {
		entity = pde;
	}
	
	public void updataData(String name, String surname, String email, String idid, String pesel, String adres){
		entity.setName(name);
		entity.setSurname(surname);
		entity.setPESEL(pesel);
		entity.setAdress(adres);
		entity.setCardID(idid);
		entity.setMail(email);	
	}
	
	public PersonalData() {
		entity = new PersonalDataEntity();
	}

	public String getName() {
		return entity.getName();
	}

	public String getSurname() {
		return entity.getSurname();
	}

	public String getPESEL() {
		return entity.getPESEL();
	}

	public String getAdress() {
		return entity.getAdress();
	}

	public String getCardID() {
		return entity.getCardID();
	}

	public String getMail() {
		return entity.getMail();
	}

	public void setName(String data) {
		entity.setName(data);
	}

	public void setSurname(String data) {
		entity.setName(data);
	}

	public void setPESEL(String data) {
		entity.setPESEL(data);	
	}

	public void setAdress(String data) {
		entity.setAdress(data);
	}

	public void setCardID(String data) {
		entity.setCardID(data);
	}

	public void setMail(String data) {
		entity.setMail(data);
	}

	public void serialize() {

	}
	
	public void create(){
		App.getInstance().getUsers().getPersonalDataController().create(entity);
	}

}