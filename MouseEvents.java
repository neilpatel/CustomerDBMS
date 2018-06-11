/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Desktop;


// Start of MouseEvents Class
@SuppressWarnings("serial")
public class MouseEvents extends UserInterface {
	private File csvFile;					// CSV File
	private int index;						// Index/Position Counter
	private String selectedField;			// Keep track of each field
	private Customer [] customers;			// Array of sorted Customers
	private Customer [] unsortedCustomers;	// Array of unsorted Customers

// Constructors
	public MouseEvents() {
		openFileAction();
		clearAction();
		exitAction();
		aboutAction();
		exportHTML();
		buttonSearchAction();
		mouseWheelAction();
		buttonFirstAction();
		buttonPreviousAction();
		buttonNextAction();
		buttonLastAction();
		buttonGoToAction();
		sortAction();
		checkBoxSortAction();
	}

// Action Listener Functionality
//Open File
	private void openFileAction() {
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));	// CTRL O key-bind
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
				jFileChooser.setFileFilter(new FileNameExtensionFilter("CSV files (.csv)", "csv"));
				jFileChooser.setAcceptAllFileFilterUsed(false);
				jFileChooser.setDialogTitle("Open File");
					
				if(jFileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION){ 
					try {
						csvFile = jFileChooser.getSelectedFile();
						FileParser f = new FileParser(csvFile);
						customers = f.getCustomers();
						unsortedCustomers = customers.clone();	// Copy the original version of file. 
					} catch(Exception e) {
						showErrorDialog();	// Send Error message in case user provides a invalid formatted csv file
					}
					index = 0;
					displayCustomer(customers[index]);   // Display the customer information based on the current index
				}
			}
		});
	}

// Clear Action (Customers)
	private void clearAction () {
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				customers = null;
				clearCustomer();
			}
		});
	}

// Exit Action
	private void exitAction() {
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		});
	}

// About Action 
	private void aboutAction() {
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				showAboutMessage();
			}
		});
	}

// HTML Export -- Generate directory (.html)
	private void exportHTML () {
		mntmHTML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else try {
					if(new File("HTML Directory").exists()) {			// Does the directory exist?
						if(showDuplicateDirectoryFoundMessage() == 1){	// Ask the user if they want to override the current file they requested
							ExportHTML exportHTML = new ExportHTML();
							exportHTML.generateDirectory(csvFile);		// If acceptable, directory will be confirmed
							showSuccessMessage();						// Inform user that the directory is confirmed 
						}
					} else {	// directory doesn't already exist
						ExportHTML exportHTML = new ExportHTML();
						exportHTML.generateDirectory(csvFile);			// Generate the directory
						showSuccessMessage();							// Inform user that the directory is confirmed
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
// Search Action
	private void buttonSearchAction() {
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null) {
					// do nothing
				} else {
					if(textFieldSearch.getText().isEmpty()){
						// do nothing
					} else {
						int previousIndex = index;		// Locate index in customer array
						String inputSearch = textFieldSearch.getText().toLowerCase();	// Get String from textFieldSearch
						String[] tokens = inputSearch.split(" ");		// Split the name input if user enters full name in one shot
						selectedField = (String) sortComboBox.getSelectedItem(); 	// get the selected field that is in the combo box
						index = -1;			// Set the index counter
						
						if(selectedField == "None"){
							try {
								FileParser f = new FileParser(csvFile);
								customers = f.getCustomers();
							} catch(Exception e) {
								showErrorDialog();	// error message
							}
						}
							
						for(int i=0; i<customers.length; i++){
							if(inputSearch.equals(customers[i].getFirstName().toLowerCase()) 			// first name
									|| inputSearch.equals(customers[i].getLastName().toLowerCase()) 	// last name
									|| inputSearch.equals(customers[i].getAddress().toLowerCase()) 		// address
									|| inputSearch.equals(customers[i].getCity().toLowerCase()) 		// city
									|| inputSearch.equals(customers[i].getState().toLowerCase()) 		// state
									|| inputSearch.equals(customers[i].getZip().toLowerCase())			// zip 
									|| inputSearch.equals(customers[i].getCounty().toLowerCase())		// county
									|| inputSearch.equals(customers[i].getCompany().toLowerCase())		// company
									|| inputSearch.equals(customers[i].getPhone().toLowerCase())		// phone
									|| inputSearch.equals(customers[i].getEmail().toLowerCase()) 		// email
									|| inputSearch.equals(customers[i].getFax().toLowerCase()) 			// fax
									|| inputSearch.equals(customers[i].getWeb().toLowerCase())			// web
									|| (tokens[0].equals(customers[i].getFirstName().toLowerCase()) && tokens[1].equals(customers[i].getLastName().toLowerCase()))){	// first and last name
									index = i;
									break;
							}
						}
							
						if(index == -1){
							index = previousIndex;
							customerNotFound.setText("Customer not found!");
						}
						else {
							displayCustomer(customers[index]);   // Display the customer information based on the current index
						}
					}
				}
			}
		});	
	}

// Jump to next or previous customers using scroll wheel
	private void mouseWheelAction() {
		contentPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(customers == null){} // do nothing
				else {
					int wheelRotation = e.getWheelRotation();
					if(wheelRotation > 0) { //	If the rotation count is positive, go to previous customer
						if(index <= 0) index = 0;
						else index--;
						displayCustomer(customers[index]);   // Display the customer information based on the current index		
					}
					
					if(wheelRotation < 0) {	// If the rotation count is negative, go the next customer
						if(index >= customers.length-1) index = customers.length-1;
						else index++;
						displayCustomer(customers[index]);   // Display the customer information based on the current index 
					}
				}
			}
		});
	}
	
	// Navigate to the first index
	private void buttonFirstAction() {
		buttonFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else {
					index = 0;
					displayCustomer(customers[index]);   // Display the customer information based on the current index
				}
			}
		});
	}
	
	// Navigate to the previous index
	private void buttonPreviousAction() {
		buttonPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else {
					if(index <= 0) index = 0;
					else index--;
					displayCustomer(customers[index]);   // Display the customer information based on the current index	
				}
			}
		});
	}
	
	// Navigate to the next index
	private void buttonNextAction() {
		buttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else {
					if(index >= customers.length-1) index = customers.length-1;
					else index++;
					displayCustomer(customers[index]);   // Display the customer information based on the current index	
				}
			}
		});
	}

	// Navigate to the last index
	private void buttonLastAction() {
		buttonLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else {
					index = customers.length-1;
					displayCustomer(customers[index]);   // Display the customer information based on the current index	
				}
			}
		});
	}

	// Navigate to a particular index (customer file)
	private void buttonGoToAction() {
		buttonGoTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(customers == null){} // do nothing
				else {
					if(textFieldGoToRecord.getText().isEmpty()){} // do nothing
					else if(textFieldGoToRecord.getText().matches("\\d+")) {	// checks if the input is an integer
						int inputGoToRecord = Integer.parseInt(textFieldGoToRecord.getText());
						
						if(inputGoToRecord <= 0 || inputGoToRecord > customers.length) txtRecordOf.setText("Invalid Input");	// Check if the index is within the bounds
						else {
							index = inputGoToRecord-1;
							displayCustomer(customers[index]);   // Display the customer information based on the current index	
						}
					}
					else txtRecordOf.setText("Invalid Input");	// Checks for an integer
				}
			}
		});
	}
	
	// Sort customers
	private void sortAction() {
		sortComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedField = (String) sortComboBox.getSelectedItem(); // get the selected field from JComboBox
				index = 0;
				
				if(customers == null){} // do nothing
				else {
					switch (selectedField) {
					case "None":		// displays the unsorted array of customers
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "First Name":	// sort by First Name
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new FirstName(SortOrder.descending));
						else Arrays.sort(customers, new FirstName(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Last Name":	// sort by Last Name
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new LastName(SortOrder.descending));
						else Arrays.sort(customers, new LastName(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Company":		// sort by Company
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Company(SortOrder.descending));
						else Arrays.sort(customers, new Company(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Address":		// sort by Address
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Address(SortOrder.descending));
						else Arrays.sort(customers, new Address(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "City":		// sort by City
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new City(SortOrder.descending));
						else Arrays.sort(customers, new City(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "County":		// sort by County
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new County(SortOrder.descending));
						else Arrays.sort(customers, new County(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "State":		// sort by State
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new State(SortOrder.descending));
						else Arrays.sort(customers, new State(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "ZIP":			// sort by ZIP
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Zip(SortOrder.descending));
						else Arrays.sort(customers, new Zip(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Phone":		// sort by Phone
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Phone(SortOrder.descending));
						else Arrays.sort(customers, new Phone(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Fax":			// sort by Fax
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Fax(SortOrder.descending));
						else Arrays.sort(customers, new Fax(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Email":		// sort by Email
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Email(SortOrder.descending));
						else Arrays.sort(customers, new Email(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					case "Web":			// sort by Web
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Web(SortOrder.descending));
						else Arrays.sort(customers, new Web(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index	
						break;
					}
				}
			}
		});
	}
	
	// Descending sort
	private void checkBoxSortAction() {
		chckbxDescending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedField = (String) sortComboBox.getSelectedItem(); //get the selected field from JComboBox
				index = 0;
				
				if(customers == null){} // do nothing
				else {
					switch (selectedField) {
					case "None":		// displays the unsorted array of customers
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "First Name":	// sort by First Name
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new FirstName(SortOrder.descending));
						else Arrays.sort(customers, new FirstName(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Last Name":	// sort by Last Name
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new LastName(SortOrder.descending));
						else Arrays.sort(customers, new LastName(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Company":		// sort by Company
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Company(SortOrder.descending));
						else Arrays.sort(customers, new Company(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Address":		// sort by Address
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Address(SortOrder.descending));
						else Arrays.sort(customers, new Address(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "City":		// sort by City
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new City(SortOrder.descending));
						else Arrays.sort(customers, new City(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "County":		// sort by County
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new County(SortOrder.descending));
						else Arrays.sort(customers, new County(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "State":		// sort by State
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new State(SortOrder.descending));
						else Arrays.sort(customers, new State(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "ZIP":			// sort by ZIP
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Zip(SortOrder.descending));
						else Arrays.sort(customers, new Zip(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Phone":		// sort by Phone
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Phone(SortOrder.descending));
						else Arrays.sort(customers, new Phone(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Fax":			// sort by Fax
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Fax(SortOrder.descending));
						else Arrays.sort(customers, new Fax(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Email":		// sort by Email
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Email(SortOrder.descending));
						else Arrays.sort(customers, new Email(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					case "Web":			// sort by Web
						if(chckbxDescending.isSelected()) Arrays.sort(customers, new Web(SortOrder.descending));
						else Arrays.sort(customers, new Web(SortOrder.ascending));
						displayCustomer(customers[index]);   // Display the customer information based on the current index
						break;
					}
				}
			}
		});
	}
	

	// Decides whether to display a customer from the unsorted array
	private void displayCustomer(Customer c) {
		if(selectedField == "None") displayCustomerHelper(unsortedCustomers[index]);
		else displayCustomerHelper(customers[index]);
	}
	
	// Displays customer information in the corresponding text fields
	private void displayCustomerHelper(Customer c) {
		textFieldFirstName.setText(c.getFirstName());
		textFieldLastName.setText(c.getLastName());
		textFieldCompany.setText(c.getCompany());
		textFieldAddress.setText(c.getAddress());
		textFieldCity.setText(c.getCity());
		textFieldCounty.setText(c.getCounty());
		textFieldState.setText(c.getState());
		textFieldZip.setText(c.getZip());
		textFieldPhone.setText(c.getPhone());
		textFieldFax.setText(c.getFax());
		textFieldEmail.setText(c.getEmail());
		textFieldWeb.setText(c.getWeb());
		int displayedRecord = index + 1;
		txtRecordOf.setText("Record " + displayedRecord + " of " + customers.length);	// Updates txtRecordOf
		clearUserInput();
	}
	
	// Clears the customer text fields
	private void clearCustomer() {
			textFieldFirstName.setText("");
			textFieldLastName.setText("");
			textFieldCompany.setText("");
			textFieldAddress.setText("");
			textFieldCity.setText("");
			textFieldCounty.setText("");
			textFieldState.setText("");
			textFieldZip.setText("");
			textFieldPhone.setText("");
			textFieldFax.setText("");
			textFieldEmail.setText("");
			textFieldWeb.setText("");
			txtRecordOf.setText("");
	}
	
	// Clears all possible user input
	private void clearUserInput() {
		textFieldGoToRecord.setText("");
		textFieldSearch.setText("");
		customerNotFound.setText("");
	}
	
	// Error dialog (in case the user has opted to select a non-valid file)
	// UPDATE: not very useful now that the "All Files" option is disabled.
	// Kept it in case the .csv file is not properly formatted or is empty
	private void showErrorDialog() {
		String errorMessage = "An error has occured while trying to read specified the file.\nPlease make sure you are using a valid .csv file and try again.";			
		JOptionPane.showMessageDialog(contentPane, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	// Developer information dialog
	private void showAboutMessage() {
		String aboutMessage = "Developed by Neil Patel\nLast updated on 6/11/2018";
		JOptionPane.showMessageDialog(contentPane, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Displays confirmation message for Export HTML Directory and launches the index.html file
	private void showSuccessMessage() throws IOException {
		String successMage = "HTML directory has been successfully created.";
		JOptionPane.showMessageDialog(contentPane, successMage, "Success", JOptionPane.INFORMATION_MESSAGE);
		Desktop.getDesktop().browse(new File("index.html").toURI());
	}
	
	// Displays a confirmation prompt and returns which option was chosen (YES/NO)
	private int showDuplicateDirectoryFoundMessage() {
		String duplicatedDirectoryFound = "HTML directory already exists, do you want to override?";
		if(JOptionPane.showConfirmDialog(contentPane, duplicatedDirectoryFound, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) return 1;
		else return 0;
		}
}