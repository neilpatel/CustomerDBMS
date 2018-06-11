/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class Phone implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public Phone(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String phone1 = c1.getPhone();
		String phone2 = c2.getPhone();
		
		if(sortOrder == SortOrder.ascending){
			return phone1.compareTo(phone2);
		} else {
			return phone2.compareTo(phone1);
		}
	}
}