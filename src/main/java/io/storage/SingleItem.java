package io.storage;

import java.util.List;

import io.workshop.FixCommision;

public class SingleItem {

	private String Name;

	private boolean Available;

	private List<ItemAssignment> History;
	private List<FixCommision> fixingHistory;

	private String Description;

	private Storage storage;

	private ItemType itemType;

	private ItemAssignment[] itemAssignment;

	public SingleItem(String startName, boolean startAvailable, String startDescription, ItemType startItemType) {

		this.setName(startName);
		this.setAvailable(startAvailable);
		this.setDescription(startDescription);
		this.setItemType(startItemType);
	}

	public Void addObject() {
		return null;
	}

	public ItemType getType() {
		return null;
	}

	public Void removeObject() {
		return null;
	}

	public Void modificateName(String startName) {
		this.setName(startName);
		return null;
	}

	public Void modificateAvailability(boolean startAvability) {
		this.setAvailable(startAvability);
		return null;
	}

	public Void modificateDescription() {
		return null;
	}

	public ItemAssignment[] getItemAssignment() {
		return itemAssignment;
	}

	public void setItemAssignment(ItemAssignment[] itemAssignment) {
		this.itemAssignment = itemAssignment;
	}

	public String getItemType() {
		return itemType.toString();
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public List<FixCommision> getFixingHistory() {
		return fixingHistory;
	}

	public void setFixingHistory(List<FixCommision> fixingHistory) {
		this.fixingHistory = fixingHistory;
	}

	public List<ItemAssignment> getHistory() {
		return History;
	}

	public void setHistory(List<ItemAssignment> history) {
		History = history;
	}

	public boolean isAvailable() {
		return Available;
	}

	public void setAvailable(boolean available) {
		Available = available;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
