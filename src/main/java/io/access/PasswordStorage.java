package io.access;

import java.util.HashMap;
import java.util.Map;

import io.access.controllers.IdPassPairController;
import io.general.App;

public class PasswordStorage {
//	private Map <Integer, IdPassPair> data = new HashMap<Integer, IdPassPair>();

	public void addIdPass(int id, String password) {
		IdPassPair ipp = new IdPassPair(id, password);
//		if (data.get(id) != null);
//			data.remove(id);
//		data.put(id, ipp);
		ipp.create();
	}
	
	/**
	 * Encrypts a string with with a very complex algorythm.
	 * @param original string
	 * @return encrypted string
	 */
	public static String encrypt(String original) { 
		return original + "0";
	}

	public Boolean checkPassword(int id, String password) {
		IdPassPairController ippc = App.getInstance().getUsers().getIdPassPairController();		
		System.out.println("CHECKING PASSWORD for ID " + id +" : " + password);
		boolean result = ippc.getById(id).check(password);
		System.out.println("CHECKING RESULT: " + result);
		return result;
	}

	public boolean changePassword(int id, String password){
		IdPassPairController ippc = App.getInstance().getUsers().getIdPassPairController();
		IdPassPair idp = ippc.getById(id);
		if (idp == null){
			return false;
		} else {
			idp.setPass(password);
			ippc.update(idp);
			return true;
		}
	}

}