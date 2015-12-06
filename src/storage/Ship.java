package storage;

import java.util.Collection;
import java.util.List;

public class Ship extends SingleItem {

	public Ship(String startName, boolean startAvailable, String startDescription, ItemType startItemType) {
		super(startName, startAvailable, startDescription, startItemType);
		// TODO Auto-generated constructor stub
	}

	public List<String> getDocumentsNeeded() {
		return DocumentsNeeded;
	}

	public void setDocumentsNeeded(List<String> documentsNeeded) {
		DocumentsNeeded = documentsNeeded;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}

	private int Capacity;

	private List<String> DocumentsNeeded;

}
