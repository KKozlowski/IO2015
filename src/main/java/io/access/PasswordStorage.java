package io.access;

import java.util.HashMap;
import java.util.Map;

public class PasswordStorage {
	private Map <Integer, IdPassPair> data = new HashMap<Integer, IdPassPair>();

	public void addIdPass(int id, String password) {
		IdPassPair ipp = new IdPassPair(id, password);
		if (data.get(id) != null);
			data.remove(id);
		data.put(id, ipp);
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
		System.out.println("CHECKING PASSWORD for ID " + id +" : " + password);
		boolean result = data.get(id).check(password);
		System.out.println("CHECKING RESULT: " + result);
		return result;
	}

	public void serialize() {

	}

}
