package access;

import java.util.Dictionary;
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

	public static String encrypt(String original) {
		return null;
	}

	public Boolean checkPassword(int id, String password) {
		return data.get(id).check(encrypt(password));
	}

	public void serialize() {

	}

}
