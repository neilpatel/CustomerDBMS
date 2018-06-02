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

	}

// Clear Action (Customers)
	private void clearAction () {

	}

// Exit Action
	private void exitAction() {

	}

// About Action 
	private void aboutAction() {

	}

// HTML Export
	private void htmlExport () {
		
	}
}