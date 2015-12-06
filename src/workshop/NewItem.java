package workshop;

import java.util.Date;

public class NewItem {
	
	public NewItem(String Name, int nrID, Date ServiceDate, String Client, String AdditionalInfo){
		this.Name = Name;
		this.nrID = nrID;
		this.ServiceDate = ServiceDate;
		this.Client = Client;
		this.AdditionalInfo = AdditionalInfo;
		
	}

	private String Name;

	private int nrID;

	private Date ServiceDate;

	private String Client;

	private String AdditionalInfo;

}
