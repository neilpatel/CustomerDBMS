/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class Web implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public Web(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String web1 = c1.getWeb();
		String web2 = c2.getWeb();
		
		if(sortOrder == SortOrder.ascending)return web1.compareTo(web2);
		else return web2.compareTo(web1);
	}
}