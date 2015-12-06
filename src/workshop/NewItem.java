package workshop;

import java.util.Date;


public class NewItem {
	
	public NewItem(String name, int nrID, Date serviceDate, String client, String additionalInfo){
		this.name = name;
		this.nrID = nrID;
		this.serviceDate = serviceDate;
		this.client = client;
		this.additionalInfo = additionalInfo;
		
	}
	
	public NewItem(){}

	private String name;
	
	private int nrID;

	private Date serviceDate;

	private String client;

	private String additionalInfo;

}
