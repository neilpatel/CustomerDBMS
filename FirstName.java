/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class FirstName implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public FirstName(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String firstName1 = c1.getFirstName();
		String firstName2 = c2.getFirstName();
		
		if(sortOrder == SortOrder.ascending) {
			return firstName1.compareTo(firstName2);
		} else { 
			return firstName2.compareTo(firstName1);
		}
	}
}