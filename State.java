/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Comparator;

public class State implements Comparator<Customer> {
	SortOrder sortOrder;
	
	public State(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Customer c1, Customer c2) {
		String state1 = c1.getState();
		String state2 = c2.getState();
		
		if(sortOrder == SortOrder.ascending){
			return state1.compareTo(state2);
		} else {
			return state2.compareTo(state1);
		}
	}
}