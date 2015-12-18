package io.storage;

public class Tool extends SingleItem {

	public Tool(String startName, boolean startAvailable, String startDescription, ItemType startItemType, int number) {
		super(startName, startAvailable, startDescription, startItemType);
		Number = number;
	}


	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	private int Number;

}
