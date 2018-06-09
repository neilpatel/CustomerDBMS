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
		String state = customers[0].getState(); 
		String currentDirectory = "HTML Directory/" + state;
		new File(currentDirectory).mkdir();
		
		//ToDo: Print in HTML format.  use 'htmlIndex.print()'
			// Need to have a html file with the necessary css file.
			// Progress: Working on the html file
		
	}
	
	// export2HTML Function to create a html page with the customer fields
	
// HTML Exporting Helper functions

	// Return HTML heading element
	private String titleHeading(String heading) {
		return "<h1>" + heading + "</h1><br/>";
	}
	
	// Return HTML anchor element
	private String hyperlink(String url, String linkText) {
		return "<a href=\"" + url + "\">" + linkText + "</a>";
	}
	
	// Return HTML anchor element for Email
	private String emailLink(String emailAddress, String subject, String linkText) {
		return "<a href=\"mailto:" + emailAddress + "?Subject=" + subject + "\">" + linkText + "</a>";
	}
	
	// Return HTML anchor element for Google Maps
	private String cityMapLink(String city, String state, String county, String linkText) {
		return "<a href=\"https://www.google.com/maps/place/" + city + ",+" + state + ",+" + county + "\" target=\"_blank\">" + linkText + "</a>";
	}

}