package io.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
		for (int i = 0; i<Amount.size(); i++){
			//showing += Amount.get(i).getName() + " " + Amount.get(i).isAvailable() + " " + Amount.get(i).getDescription();
			System.out.println(Amount.get(i).getName() + " " + Amount.get(i).isAvailable() + " " + Amount.get(i).getDescription());
		}
	}
	
	
	public void addObject(SingleItem SingleObject) {
		if(!App.getInstance().getUsers().doesCurrentUserHavePermission(PermissionType.storageWorker))
			return;
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

	@Override
	public String toString() {
		String showing = new String();
		for (int i = 0; i<Amount.size(); i++){
			showing += "Nazwa: " + Amount.get(i).getName() + " " + "dostêpny: " +  Amount.get(i).isAvailable() + " " + "opis: "+ Amount.get(i).getDescription() + '\n' + System.lineSeparator();	
		}
		return showing;
	}

}
