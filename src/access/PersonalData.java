package access;

public class PersonalData {

	private String name;

	private String surname;

	private String pesel;

	private String adress;

	private String idCardID;

	private String mail;
	
	public PersonalData(String name, String surname, String pesel, String adress, String idCardID, String mail) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.adress = adress;
		this.idCardID = idCardID;
		this.mail = mail;
	}
	
	public PersonalData() {
	
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

	public void serialize() {

	}

}