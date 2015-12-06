package workshop;

import java.util.Date;
import storage.SingleItem;

public class FixCommision {

	private String Name;

	private Boolean InProgress;

	private String AdditionalInfo;

	private Date DateStarted;

	private Date DateEnded;

	private SingleItem Item;

	private NewItem newItem;

	public void EditProgress(boolean Progress) {
		InProgress = Progress;
	}

	public void EditInfo(String Info) {
		AdditionalInfo = Info;
	}

	public void EditDateEnded(Date EndDate) {
		DateEnded = EndDate;
	}

	public void ItemAssignment(SingleItem AssignedItem) {
		Item = AssignedItem;
	}

	public void AddNewItem() {

	}

}
