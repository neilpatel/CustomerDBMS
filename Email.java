/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class Email implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public Email(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String email1 = c1.getEmail();
		String email2 = c2.getEmail();
		
		if(sortOrder == SortOrder.ascending){
			return email1.compareTo(email2);
		} else {
			return email2.compareTo(email1);
		}
	}
}