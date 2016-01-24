package io.storage.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.storage.SingleItem;

@Entity
@Table(name = "Storage")
public class StorageManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "available")
	private Boolean available;

	@Column(name = "description")
	private String description;

	@Column(name = "itemType")
	private String itemType;

	@Transient
	private SingleItem item;

	public StorageManagement() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public Boolean available() {
		return available;
	}

	public void available(Boolean available) {
		this.available = available;
	}

	public String description() {
		return description;
	}

	public void description(String description) {
		this.description = description;
	}

	public String itemType() {
		return description;
	}

	public void itemType(String itemType) {
		this.itemType = itemType;
	}

	// na czas testu
	public StorageManagement(String name, boolean available) {
		this.name = name;
		this.available = available;
	}

	public void editAvailable(boolean Available) {
		Available = Available;
	}

}