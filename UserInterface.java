/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

// import statements
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


// Start of User Interface GUI Class
@SupressWarnings({ "rawtypes", "unchecked", "serial" })
public class UserInterface extends JFrame {
		protected JPanel contentPane;
	protected JMenuBar menuBar;
	protected JMenu mnFile;
	protected JMenu mnHelp;
	protected JMenuItem mntmOpen;
	protected JMenuItem mntmReset;
	protected JMenuItem mntmClear;
	protected JMenuItem mntmExit;
	protected JMenuItem mntmAbout;
	protected JMenuItem mntmHTML;
	protected JButton buttonSearch;
	protected JButton buttonFirst;
	protected JButton buttonPrevious;
	protected JButton buttonNext;
	protected JButton buttonLast;
	protected JButton buttonGoTo;
	protected JLabel customerNotFound;
	protected JLabel lblSort;
	protected JComboBox sortComboBox;
	protected JCheckBox chckbxDescending;
	protected JTextField textFieldSearch;
	protected JTextField textFieldFirstName;
	protected JTextField textFieldLastName;
	protected JTextField textFieldAddress;
	protected JTextField textFieldCity;
	protected JTextField textFieldState;
	protected JTextField textFieldZip;
	protected JTextField textFieldCounty;
	protected JTextField textFieldCompany;
	protected JTextField textFieldPhone;
	protected JTextField textFieldEmail;
	protected JTextField textFieldFax;
	protected JTextField textFieldWeb;
	protected JTextField txtRecordOf;
	protected JTextField textFieldGoToRecord;
	protected String [] sortLabel = {"None", "First Name", "Last Name", "Company", "Address", "City", "County", "State", "ZIP", "Phone", "Fax", "Email", "Web"};
	
}