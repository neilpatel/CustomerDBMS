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
		FileParser f = new FileParser(csvFile);
		Customer[] customers = f.getCustomers();
		Arrays.sort(customers, new State(SortOrder.ascending));
		
		new File("HTML Directory/").mkdir();
		PrintStream htmlIndex = new PrintStream(new File("OpenMeUpForWebDirectory.html"));
		String state = customers[0].getState(); 
		String currentDirectory = "HTML Directory/" + state;
		new File(currentDirectory).mkdir();
		
		htmlIndex.print(	"<!DOCTYPE html>\n"
						+	"<html>\n"
						+		"\t<head>\n"
						+			"\t\t<title>Index</title>\n"
						+			"\t\t<meta charset=\"utf-8\" />\n"
						+			"\n"
									//// CSS STYLING
						+			"\t\t<style type=\"text/css\">\n"
										// BODY
						+				"\t\t\tbody {\n"
						+					"\t\t\t\tbackground-color:#333333;\n"
						+					"\t\t\t\tfont-family:tahoma, helvetica, sans-serif;\n"
						+					"\t\t\t\tpadding:20px;\n"
						+					"\t\t\t\tmargin:0;\n"
						+ 				"\t\t\t}\n"
										// END OF BODY
						+				"\n"
										// #MAIN
						+				"\t\t\t#main {\n"
						+					"\t\t\t\tbackground-color:white;\n"
						+					"\t\t\t\tcolor:black;\n"
						+					"\t\t\t\ttext-align:center;\n"
						+					"\t\t\t\tmargin:auto;\n"
						+					"\t\t\t\twidth:30%;\n"
						+					"\t\t\t\tborder-radius:20px;\n"
						+					"\t\t\t\tpadding:20px;\n"
						+ 				"\t\t\t}\n"
										// END OF #MAIN
						+				"\n"
										// ANCHOR
						+				"\t\t\ta {\n"
						+					"\t\t\t\tline-height:1em;\n"
						+					"\t\t\t\tdisplay:inline-block;\n"
						+					"\t\t\t\tpadding:10px;\n"
						+					"\t\t\t\twidth:90%;\n"
						+					"\t\t\t\ttext-decoration:none;\n"
						+					"\t\t\t\tcolor:black;\n"
						+ 				"\t\t\t}\n"
										// END OF ANCHOR
						+				"\n"
										// A:HOVER
						+				"\t\t\ta:hover {\n"
						+					"\t\t\t\tcolor:white;\n"
						+					"\t\t\t\tbackground-color:#333333;\n"
						+ 				"\t\t\t}\n"
										// END OF A:HOVER
						+				"\n"
										// H1
						+				"\t\t\th1 {\n"
						+					"\t\t\t\tfont-size:3em;\n"
						+					"\t\t\t\tpadding:5px;\n"
						+					"\t\t\t\tborder-top:4px solid #333333;\n"
						+ 				"\t\t\t}\n"
										// END OF H1
						+			"\t\t</style>\n"
									//// END OF CSS
						+		"\t</head>\n"
						+		"\t<body>\n"
									//// MAIN DIV
						+			"\t\t<div id=\"main\">\n"
						+				"\t\t\t" + titleHeading(state) + "\n");
					
		
		// Iterate through the sorted array
		for(int i=1; i<customers.length; i++) {	
			if(!state.equals(customers[i].getState())) {					// Conditional to check if the state has changed
				state = customers[i].getState();							// Set the current state value
				htmlIndex.print(		"\t\t\t" + titleHeading(state) + "\n");	// Create HTML header element with the current value
				currentDirectory = "HTML Directory/" + state;				// Update the currentDirectory value
				new File(currentDirectory).mkdir();							// Make a new folder with its respective current state
			}
			
			// Create HTML page with the current customer information and update the necessary fields
			exportToHtml(customers[i], currentDirectory);
			htmlIndex.print(			"\t\t\t" + hyperlink(currentDirectory + "/" + customers[i].getPhone() + ".html", customers[i].getFirstName() + " " + customers[i].getLastName()) + "<br/>\n");
		}	
		
		htmlIndex.print(			"\t\t</div>\n"
									//// END OF MAIN DIV
						+		"\t</body>\n"
						+	"</html>");
		htmlIndex.close();
		
	}
	
	// Create HTML page with the current customer information and update the necessary fields
	private void exportToHtml(Customer customer, String currentDirectory) throws FileNotFoundException {
		PrintStream html = new PrintStream(new File(currentDirectory + "/" + customer.getPhone() + ".html"));
		String template = 		"<!DOCTYPE html>\n"
							+	"<html>\n"
							+		"\t<head>\n"
							+			"\t\t<title>" + customer.getFirstName() + " " + customer.getLastName() + "</title>\n"
							+			"\t\t<meta charset=\"utf-8\" />\n"
							+			"\n"
										//// CSS STYLING
							+			"\t\t<style type=\"text/css\">\n"
											// BODY
							+				"\t\t\tbody {\n"
							+					"\t\t\t\tbackground-color:#333333;\n"
							+					"\t\t\t\tfont-family:tahoma, helvetica, sans-serif;\n"
							+					"\t\t\t\tpadding:20px;\n"
							+					"\t\t\t\tmargin:0;\n"
							+ 				"\t\t\t}\n"
											// END OF BODY
							+				"\n"
											// #MAIN
							+				"\t\t\t#main {\n"
							+					"\t\t\t\tbackground-color:white;\n"
							+					"\t\t\t\tcolor:black;\n"
							+					"\t\t\t\ttext-align:center;\n"
							+					"\t\t\t\tmargin:auto;\n"
							+					"\t\t\t\twidth:30%;\n"
							+					"\t\t\t\tborder-radius:20px;\n"
							+					"\t\t\t\tpadding:20px;\n"
							+ 				"\t\t\t}\n"
											// END OF #MAIN
							+				"\n"
											// #FOOTER
							+				"\t\t\t#footer {\n"
							+					"\t\t\t\tfont-size:12px;\n"
							+					"\t\t\t\tpadding:5px;\n"
							+					"\t\t\t\tcolor:#666666;\n"
							+ 				"\t\t\t}\n"
											// END OF #FOOTER
							+			"\t\t</style>\n"
										//// END OF CSS
							+		"\t</head>\n"
							+		"\t<body>\n"
										//// MAIN DIV
							+			"\t\t<div id=\"main\">\n"
							+				"\t\t\t" + titleHeading(customer.getFirstName() + " " + customer.getLastName())+ "<br/>\n"
							+				"\t\t\t" + customer.getCompany() + "<br/>\n"
							+				"\t\t\t" + customer.getAddress() + "<br/>\n"
							+				"\t\t\t" + customer.getCity() + ", " + customer.getState() + " " + customer.getZip() + "<br/>\n"
							+				"\t\t\tPhone: " + customer.getPhone() + "<br/>\n"
							+				"\t\t\tFax: " + customer.getFax() + "<br/><br/>\n"
							+				"\t\t\t" + emailLink(customer.getEmail(), "Hello from Bob", customer.getEmail()) + "<br/>\n"
							+				"\t\t\t" + hyperlink(customer.getWeb(), customer.getWeb()) + "<br/><br/>\n"
							+				"\t\t\t" + cityMapLink(customer.getCity(), customer.getState(), customer.getCounty(), "Click here to get a map of " + customer.getCity() + ", " + customer.getState()) + "<br/><br/>\n"
							+				"\t\t\t" + hyperlink("../../index.html", "Go back") + "<br/><br/>\n"
							+				"\t\t\t<div id=\"footer\"><b>Created by</b> " + emailLink("neilpatel@pitt.edu", "Keep it up, Neil!", "Neil Patel") +"<b> | Customer DBMS| Last updated on 6/10/2018 </b></div>\n"
							+			"\t\t</div>\n"
										//// END OF MAIN DIV
							+		"\t</body>\n"
							+	"</html>";
		html.print(template);
		html.close();
	}
	
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