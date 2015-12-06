package storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Storage {

	public Storage(String name) {
		super();
		Name = name;
	}

	

	private ArrayList<SingleItem> Amount = new ArrayList<SingleItem>();

	private String Name;

	private SingleItem[] singleItem;

	public void removeObject(int i) {
		Amount.remove(i);
	}

	public void showStorage() {
	
		for (int i = 0; i<Amount.size(); i++){
			System.out.println(Amount.get(i).getName() + " " + Amount.get(i).isAvailable() + " " + Amount.get(i).getDescription());
		}
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
