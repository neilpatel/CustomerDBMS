/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class Fax implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public Fax(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String fax1 = c1.getFax();
		String fax2 = c2.getFax();
		
		if(sortOrder == SortOrder.ascending){
			return fax1.compareTo(fax2);
		} else {
			return fax2.compareTo(fax1);
		}
	}
}