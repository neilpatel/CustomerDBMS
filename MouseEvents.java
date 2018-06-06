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


// start of MouseEvents class

@SuppressWarnings("serial")
public class MouseEvents extends UserInterface {
	private File csvFile;		// CSV File
	private int index;		// Index/Position Counter
	private String selectedField;		// Keep track of each field
	private Customer [] customers;		// Array of sorted Customers
	private Customer [] unsortedCustomers;		// Array of unsorted Customers

// Constructors
	public MouseEvents() {
		openFileAction();
		clearAction();
		exitAction();
		aboutAction();
		htmlExport();
		// ToDo: search button, search button functionality, navigation options. 
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
					displayCustomer(customers[index]);
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
	private void htmlExport () {
		mntmHTML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else try {
					if(new File("HTML Directory").exists()) {			// Does the directory exist?
						if(showDuplicateDirectoryFoundMessage() == 1){	// Ask the user if they want to override the current file they requested
							HtmlExport htmlExport = new HtmlExport();
							htmlExport.generateDirectory(csvFile);		// If acceptable, directory will be confirmed
							showSuccessMessage();						// Inform user that the directory is confirmed 
						}
					} else {	// directory doesn't already exist
						HtmlExport htmlExport = new HtmlExport();
						htmlExport.generateDirectory(csvFile);			// Generate the directory
						showSuccessMessage();							// Inform user that the directory is confirmed
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//Search Action
	private void buttonSearchAction() {
		buttonSearch.addActionListener(new ActionListener()) {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null) {
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
							displayCustomer(customers[index]);
						}
					}
				}
			}
		});	

// Jump to next or previous customers using scroll wheel
	private void mouseWheelAction() {
		contentPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(customers == null){} // do nothing
				else {
					int wheelRotation = e.getWheelRotation();
					if(wheelRotation > 0) {	// previous customer
						if(index <= 0) index = 0;
						else index--;
						displayCustomer(customers[index]);
					}
					
					if(wheelRotation < 0) {	// next customer
						if(index >= customers.length-1) index = customers.length-1;
						else index++;
						displayCustomer(customers[index]);
					}
				}
			}
		});
	}
	
	// Jump to first customer button
	private void buttonFirstAction() {
		buttonFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customers == null){} // do nothing
				else {
					index = 0;
					displayCustomer(customers[index]);
				}
			}
		});
	}		
		
		
}