package io.access;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.access.controllers.IdPassPairController;
import io.general.App;

/**
 * Represents an id and pass pair for this web application.
 */
@Entity
@Table(name = "idpasspair")
public class IdPassPair {

	@Id
	private int id;

	@NotNull
	private String encryptedPass;

	public IdPassPair() { }
	
	public IdPassPair(int id, String password){
		this.id = id;
		setPass(password);
	}
	
	public int getID() {
		return id;
	}

	public Boolean check(String password) {
		System.out.println("INNER CHECK: stored " + encryptedPass + " candidate: " + PasswordStorage.encrypt(password));
		if (PasswordStorage.encrypt(password).equals(encryptedPass)) return true;
		else return false;
	}

	public void setPass(String pass) {
		encryptedPass = PasswordStorage.encrypt(pass);
	}
	
	public void create() {
		IdPassPairController ippc = App.getInstance().getUsers().getIdPassPairController();
		ippc.create(this);
	}

}
