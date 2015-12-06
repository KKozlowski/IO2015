package storage;

import java.util.Date;
import services.Service;

public class ItemAssignment {

	private Date beginning;

	private Date end;

	private Boolean serviceRent;

	private Service service;

	private SingleItem singleItem;

	public Date getBeginning() {
		return beginning;
	}

	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Boolean getServiceRent() {
		return serviceRent;
	}

	public void setServiceRent(Boolean serviceRent) {
		this.serviceRent = serviceRent;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public SingleItem getSingleItem() {
		return singleItem;
	}

	public void setSingleItem(SingleItem singleItem) {
		this.singleItem = singleItem;
	}

}
