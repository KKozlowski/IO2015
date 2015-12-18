package io.storage;

import java.util.Collection;
import java.util.List;

public class Ship extends SingleItem {

	public Ship(String startName, boolean startAvailable, String startDescription, ItemType startItemType, int capacity,
			List<String> documentsNeeded) {
		super(startName, startAvailable, startDescription, startItemType);
		Capacity = capacity;
		DocumentsNeeded = documentsNeeded;
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
