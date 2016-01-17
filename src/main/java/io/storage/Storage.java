package io.storage;

import java.util.ArrayList;

import io.access.PermissionType;
import io.general.App;

public class Storage {

	public Storage() {
		super();
	}

	private ArrayList<SingleItem> Amount = new ArrayList<SingleItem>();

	private SingleItem[] singleItem;

	public void removeObject(int i) {
		Amount.remove(i);
	}

	public void showStorage() {
		//String showing = new String();
		for (int i = 0; i < Amount.size(); i++) {
			//showing += Amount.get(i).getName() + " " + Amount.get(i).isAvailable() + " " + Amount.get(i).getDescription();
			System.out.println(Amount.get(i).getName() + " " + Amount.get(i).isAvailable() + " " + Amount.get(i).getDescription());
		}
	}

	public SingleItem GetObject(int i) {
		return Amount.get(i);
	}

	public void addObject(SingleItem SingleObject) {
		if (!App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.storageWorker)) {
			return;
		}
		Amount.add(SingleObject);
	}

	public Void modificateObject(SingleItem SingleObject) {
		return null;
	}

	public SingleItem[] getSingleItem() {
		return singleItem;
	}

	public void setSingleItem(SingleItem[] singleItem) {
		this.singleItem = singleItem;
	}

	public int size() {
		return Amount.size();
	}

	public String showSpecifyItems(String typeOfItem) {
		String showing = new String();
		String currtype = new String();
		for (int i = 0; i < Amount.size(); i++) {
			currtype = Amount.get(i).getItemType();
			if (currtype.equals(typeOfItem)) {
				showing += "Nazwa: " + Amount.get(i).getName() + " dostêpny: " + Amount.get(i).isAvailable() + " opis: "
						+ Amount.get(i).getDescription() + '\n' + System.lineSeparator();
			}

		}
		return showing;
	}

	@Override
	public String toString() {
		String showing = new String();
		for (int i = 0; i < Amount.size(); i++) {
			showing += "Nazwa: " + Amount.get(i).getName() + " dostêpny: " + Amount.get(i).isAvailable() + " opis: "
					+ Amount.get(i).getDescription() + " typ: " + Amount.get(i).getItemType() + '\n' + System.lineSeparator();
		}
		return showing;
	}

}
