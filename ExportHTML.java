/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

// Start of ExportHTML class

public class ExportHTML {
	public void generateDirectory(File csvFile) throws IOException {
		FileSplitter f = new FileSplitter(csvFile);
		Customer[] customers = f.getCustomers();
		Arrays.sort(customers, new State(SortORder.ascending));
		
		new File("HTML Directory\").mkdir();
		PrintStream htmlIndex = new PrintStream(new File("index.html"));
		// work in progress
	}


}