/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.io.*;
import java.util.Scanner;

public class FileParser {
	private Scanner input;
	private File filePath;
		
	public FileParser(File file) throws FileNotFoundException {
		input = new Scanner(file);
		filePath = file;
	}
	
	public Customer[] getCustomers() throws FileNotFoundException {
		String line;
		String[] tokens;
		
		Customer[] customers = new Customer[arrayCapacity(filePath)];
		input.nextLine(); // gets rid of header
		
		for(int i=0; i<customers.length; i++){
			line = input.nextLine();					// tokenizes each line of input
			line = line.substring(1, line.length()-1);	// removes the first and last characters (")
			tokens = line.split("\",\"");				// splits each line into an array of strings to construct a customer
			customers[i] = new Customer(tokens[0],tokens[1],tokens[2],tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9], tokens[10], tokens[11]);
		}
		
		input.close();
		return customers;
	}
	
	// Determines how many customers are in the specified .csv file
	private int arrayCapacity(File filePath) throws FileNotFoundException {
		Scanner scanLines = new Scanner(filePath);
		int arraySize = -1;
		
		while(scanLines.hasNextLine()){
			arraySize++;
			scanLines.nextLine();
		}
		
		scanLines.close();
		return arraySize;
	}
}