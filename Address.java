/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class Address implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public Address(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Override
	public int compare(Customer c1, Customer c2) {
		String address1 = c1.getAddress();
		String address2 = c2.getAddress();
		
		if(sortOrder == SortOrder.ascending){
			return address1.compareTo(address2);
		} else {
			return address2.compareTo(address1);
		}
	}
}