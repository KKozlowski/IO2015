package access;

public class IdPassPair {

	private int id;

	private String encryptedPass;

	public IdPassPair(int id, String password){
		this.id = id;
		setPass(password);
	}
	
	public int getID() {
		return id;
	}

	public Boolean check(String password) {
		if (PasswordStorage.encrypt(password).equals(encryptedPass)) return true;
		else return false;
	}

	public void setPass(String pass) {
		encryptedPass = PasswordStorage.encrypt(pass);
	}

}
