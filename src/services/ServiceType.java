package services;

public class ServiceType {

	public ServiceType(int typeID, String schema, String name) {
		super();
		this.typeID = typeID;
		this.schema = schema;
		this.name = name;
	}

	private int typeID;

	private String schema;

	private String name;

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
