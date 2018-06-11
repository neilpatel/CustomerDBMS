/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class LastName implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public LastName(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String lastName1 = c1.getLastName();
		String lastName2 = c2.getLastName();
		
		if(sortOrder == SortOrder.ascending) {
			return lastName1.compareTo(lastName2);
		} else {
			return lastName2.compareTo(lastName1);
		}
	}
}