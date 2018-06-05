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

// HTML Export
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
					}
					else {	// directory doesn't already exist
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
}