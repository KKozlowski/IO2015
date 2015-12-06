package storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Storage {

	public Storage(ArrayList<SingleItem> amount, String name, SingleItem[] singleItem) {
		super();
		Amount = amount;
		Name = name;
		this.singleItem = singleItem;
	}

	private ArrayList<SingleItem> Amount;

	private String Name;

	private SingleItem[] singleItem;

	public void removeObject(int i) {
		Amount.remove(i);
	}

	public void showStorage() {
		System.out.println(Amount);
	}

	public void addObject(SingleItem SingleObject) {
		Amount.add(SingleObject);
	}

	public Void modificateObject(SingleItem SingleObject) {
		return null;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public SingleItem[] getSingleItem() {
		return singleItem;
	}

	public void setSingleItem(SingleItem[] singleItem) {
		this.singleItem = singleItem;
	}

	@Override
	public String toString() {
		return "Storage [Amount=" + Amount + ", Name=" + Name + ", singleItem=" + Arrays.toString(singleItem) + "]";
	}

}
