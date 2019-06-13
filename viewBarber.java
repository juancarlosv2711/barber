package barber;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class viewBarber extends JFrame {

	private controllerBarber controller;
	// Private object of controller is instantiated to get Action Listener in this class

	private JTable availabilityTable = new JTable();
	private JTable pendingTable = new JTable();
	//Global Table class to get option selected

	private JPanel signUpPAnel = new JPanel();
	private JPanel availabilityPanel = new JPanel();
	private JPanel eventsPanel = new JPanel();
	//Instance of Panel to call from getter methods

	private JDateChooser chooser = new JDateChooser();
	//Instance of Calendar class
	
	JComboBox timeBox;
	//Class to Select options

	private JTextField emailTF;
	private JPasswordField passwordTF;
	private JTextField nameTF;
	private JTextField addressTF;
	private JTextField mobileTF;
	//Global Instance of textfield to use in getter method

	private String[] time = { "9:00 ", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
			"14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00" };
	//Global String array to display time in available slots

	/**Default Constructor taking controller from parameter**/

	public viewBarber(controllerBarber cntrl) {
		
		this.controller = cntrl;
		//Storing controller in global Controller
	}

	/** The method for barber sign up */
	public void signupBarber() {

		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("BarberBlade");
		this.setLocationRelativeTo(null);
		//Set window in center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dispose frame on close

		this.add(signUpPAnel);
		//adding main panel to JFrame

		GridLayout frameManager = new GridLayout(0, 1);
		//Instantiating Grid layout manager one column many rows
		signUpPAnel.setLayout(frameManager);
		//Setting mainPanel to frame

		JLabel details = new JLabel("Please register your details below", SwingConstants.CENTER);
		//Label
		signUpPAnel.add(details);
		//Adding label to panels

		JPanel namePanel = new JPanel();
		JLabel nameLabel = new JLabel("Full name");
		// name label
		nameTF = new JTextField(20);
		//name textfield
		namePanel.add(nameLabel);
		namePanel.add(nameTF);
		signUpPAnel.add(namePanel);
		//Adding content to panel

		JPanel addressPanel = new JPanel();
		JLabel addressLabel = new JLabel("Address");
		//Email Label
		addressTF = new JTextField(20);
		//Email text field
		addressPanel.add(addressLabel);
		addressPanel.add(addressTF);
		signUpPAnel.add(addressPanel);
		//Adding content to panel

		JPanel mobilePanel = new JPanel();
		JLabel mobileLabel = new JLabel("Phone number");
		mobileTF = new JTextField(20);
		mobilePanel.add(mobileLabel);
		mobilePanel.add(mobileTF);
		signUpPAnel.add(mobilePanel);
		//Adding content to panel

		JPanel emailPanel = new JPanel();
		JLabel emailLabel = new JLabel("Email address");
		emailTF = new JTextField(20);
		emailPanel.add(emailLabel);
		emailPanel.add(emailTF);
		signUpPAnel.add(emailPanel);
		//Adding content to panel

		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("Password");
		passwordTF = new JPasswordField(20);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordTF);
		signUpPAnel.add(passwordPanel);
		//Adding content to panel

		JPanel buttonPanel = new JPanel();
		JButton cancel = new JButton("Cancel");
		JButton ok = new JButton("ok");
		cancel.addActionListener(controller);
		cancel.setActionCommand("cancelBarber");
		ok.addActionListener(controller);
		ok.setActionCommand("SignBarber");
		buttonPanel.add(cancel);
		buttonPanel.add(ok);
		signUpPAnel.add(buttonPanel);
		//Adding content to panel

		this.validate();
		this.repaint();

	}

	/** Main window barber logged in */
	public void availabilityWindow(String[][] data) {
		//Takes as a parameter the data from database
		
		String[] columns = { "ID", "date", "time" };
		//Array for column names in JTable

		this.setVisible(true);
		this.setSize(600, 600);
		this.setTitle("BarberBlade");
		this.setLocationRelativeTo(null);
		//Set window in center of the screen
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Dispose frame on close

		JPanel menuBar = new JPanel();

		JButton eventsButton = new JButton("Events");
		eventsButton.setActionCommand("events");
		eventsButton.addActionListener(controller);

		JButton logout = new JButton("Log out");
		logout.setActionCommand("logout");
		logout.addActionListener(controller);

		TitledBorder options = new TitledBorder("Options");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);

		menuBar.setBorder(options);
		menuBar.add(eventsButton);
		menuBar.add(logout);

		BorderLayout layout = new BorderLayout();
		//Border Layout Manager
		JPanel bottom = new JPanel();

		JPanel datePanel = new JPanel();

		TitledBorder availability = new TitledBorder("Availability");
		//Instantiating a border
		availability.setTitleJustification(TitledBorder.CENTER);
		availability.setTitlePosition(TitledBorder.TOP);

		JButton getDate = new JButton("get Date");
		getDate.setActionCommand("date");
		getDate.addActionListener(controller);

		chooser.setLocale(Locale.ENGLISH);

		timeBox = new JComboBox(time);

		datePanel.add(new JLabel("Set free Slot"));
		datePanel.add(chooser);
		datePanel.add(timeBox);
		datePanel.add(getDate);

		DefaultTableModel model = new DefaultTableModel(data, columns);
		availabilityTable.setModel(model);
		JScrollPane scroll = new JScrollPane(availabilityTable);
		//Setting up table
		JPanel boxPanel = new JPanel();

		boxPanel.add(scroll);

		bottom.setLayout(layout);
		bottom.setBorder(availability);
		bottom.add(datePanel, BorderLayout.NORTH);
		//Adding panel placed in top of main panel
		bottom.add(boxPanel, BorderLayout.CENTER);
		//Adding panel placed in center of main panel

		availabilityPanel.add(menuBar);
		availabilityPanel.add(bottom);

		this.add(availabilityPanel);
		this.validate();
		this.repaint();

	}

	/** events window */
	public void events(String[][] pendingdata, String[][] eventsdata) {
		//Takes two parameter data array from database

		String[] columnspending = { "Book ID", "Customer", "Date", "Time", "Status" };
		//Array for column names in JTable

		this.setVisible(true);
		this.setSize(600, 600);
		this.setTitle("BarberBlade");
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Dispose frame on close

		BorderLayout layout = new BorderLayout();
		//Border Layout Manager

		JPanel topPanel = new JPanel();

		TitledBorder options = new TitledBorder("Options");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);

		JButton home = new JButton("Home");
		home.setActionCommand("home");
		home.addActionListener(controller);

		JButton logout = new JButton("Log out");
		logout.setActionCommand("logout");
		logout.addActionListener(controller);

		topPanel.setBorder(options);
		topPanel.add(home);
		topPanel.add(logout);

		JPanel leftPanel = new JPanel();
		BorderLayout leftlayer = new BorderLayout();

		TitledBorder pending = new TitledBorder("Pending events");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);

		JButton cancelbooking = new JButton("Cancel booking");
		cancelbooking.setActionCommand("cancelbooking");
		cancelbooking.addActionListener(controller);

		JButton approvebooking = new JButton("Approve");
		approvebooking.setActionCommand("approve");
		approvebooking.addActionListener(controller);
		JPanel panelbuttons = new JPanel();
		panelbuttons.add(cancelbooking);
		panelbuttons.add(approvebooking);

		DefaultTableModel tablePending = new DefaultTableModel(pendingdata, columnspending);
		pendingTable.setModel(tablePending);
		JScrollPane pendingscroll = new JScrollPane(pendingTable);
		//Setting up table
		leftPanel.setLayout(leftlayer);
		leftPanel.setBorder(pending);

		leftPanel.add(panelbuttons, BorderLayout.NORTH);
		leftPanel.add(pendingscroll, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel();

		TitledBorder coming = new TitledBorder("Coming events");
		//Instantiating a border
		coming.setTitleJustification(TitledBorder.CENTER);
		coming.setTitlePosition(TitledBorder.TOP);

		DefaultTableModel tableComing = new DefaultTableModel(eventsdata, columnspending);
		JTable eventsTable = new JTable(tableComing);
		JScrollPane eventsscroll = new JScrollPane(eventsTable);
		//Setting up table

		rightPanel.setBorder(coming);
		rightPanel.add(eventsscroll);

		eventsPanel.setLayout(layout);
		eventsPanel.add(topPanel, BorderLayout.NORTH);
		//Adding panel placed in top of main panel
		eventsPanel.add(leftPanel, BorderLayout.WEST);
		//Adding panel placed in left side of main panel
		eventsPanel.add(rightPanel, BorderLayout.CENTER);
		//Adding panel placed in center of main panel

		this.add(eventsPanel);
		//Add main panel to JFrame
		this.pack();
		this.validate();
		this.repaint();

	}

	/** waiting for approval */
	public void popupapproval() {

		// Just pop-up message
		JOptionPane.showMessageDialog(null, "You have been succesfullly registered, waiting for admin approval");
	}

	/** Removes sign up panel */
	public void clearSignup() {
		
		signUpPAnel.removeAll();
		this.pack();
	}

	/** Removes availability panel */
	public void clearAvailability() {

		availabilityPanel.removeAll();
		this.pack();
	}

	/** Removes events panel */
	public void clearevents() {

		eventsPanel.removeAll();
		this.pack();
	}

	/** unsuccessful register */
	public void unSuccessfulBarber() {
		// Just pop-up message

		String[] options = { "Try Again", "Cancel" };
		int m = JOptionPane.showOptionDialog(this, "Fill in your details correctly", "Registration",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
				options, // the titles of the button
				options[1]); // default button title
		System.out.println(m);
		if (m == 0) {

			nameTF.setText("");
			addressTF.setText("");
			mobileTF.setText("");
			emailTF.setText("");
			passwordTF.setText("");
		} else {
			this.dispose();

		}

	}

	/** Get Date **/
	public String getDate() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(chooser.getDate());
	}

	/** Get Time **/
	public String getTime() {

		return (time[timeBox.getSelectedIndex()]);
	}

	/**Get name**/
	public String getName() {

		String name = nameTF.getText();
		if (name.equals("")) {
			name = "null";
		} else {
			name = "'" + name + "'";
		}

		return name;
	}

	/**Get phone**/
	public String getPhone() {

		String phone = mobileTF.getText();
		if (phone.equals("")) {
			phone = "null";
		} else {
			phone = "'" + phone + "'";
		}

		return phone;
	}

	/**Get email**/
	public String getEmail() {

		String email = emailTF.getText();
		if (email.equals("")) {
			email = "null";
		} else {
			email = "'" + email + "'";
		}
		return email;
	}

	/**Get password**/
	public String getPassword() {

		String hashed = passwordTF.getText();
		if (hashed.equals("")) {
			return hashed = "null";
		} else {
			hashed = BCrypt.hashpw(passwordTF.getText(), BCrypt.gensalt());
			return "'" + hashed + "'";
		}
	}

	/**Get location**/
	public String getLocations() {

		String location = addressTF.getText();
		if (location.equals("")) {
			location = "null";
		} else {
			location = "'" + location + "'";
		}
		return location;
	}

	/**Get row selected**/
	public Integer getSelectedRow() {

		Integer row = pendingTable.getSelectedRow();
		return row;
	}

}
