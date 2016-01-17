package io.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_types")
public class ServiceType {

	public ServiceType() {}
	public ServiceType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public ServiceType(String name) {
		super();
		this.name = name;
	}

	public ServiceType(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
