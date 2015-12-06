package storage;

import java.util.Date;
import services.Service;

public class ItemAssignment {

	public ItemAssignment(Date beginning, Date end, Boolean serviceRent, Service service, SingleItem singleItem) {
		super();
		this.beginning = beginning;
		this.end = end;
		this.serviceRent = serviceRent;
		this.service = service;
		this.singleItem = singleItem;
	}

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
