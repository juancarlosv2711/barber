package barber;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class viewCustomer extends JFrame {

	private controllerCustomer controller;
	// Private object of controller is instantiated to get Action Listener in this class
	
	private JPanel signUpPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel makeBooking = new JPanel();
	private JPanel cancelBooking = new JPanel();
	//Instance of Panel to call from getter methods
	
	private JComboBox selectBox;
	//Class to Select options
	
	private JRadioButton name;
	private JRadioButton location;
	//Radiobuttons Displaying options
	
	private JTable bookTable;
	private JTable cancelTable;
	//Global Table class to get option selected
	
	private JPasswordField passwordTF;
	private JTextField emailTF;
	private JTextField nameTF;
	private JTextField mobileTF;
	private JTextField searchBarber;
	//Global Instance of textfield to use in getter method
	
	private String[] selectOptions = {"Make a new Booking", "Cancel a Booking", "Make a Complaint"};
	private String[] selectBooking = {"Name", "Location"};
	//Global String array
	
	/**Default Constructor taking controller from parameter**/
	public viewCustomer(controllerCustomer listener) {

		this.controller = listener;
		//Storing controller in global Controller
	}
	

	/** The method for customer sign up */
	public void signupCustomer() {

		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Sign up");
		this.setLocationRelativeTo(null);
		//Set window in center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dispose frame on close

		this.add(signUpPanel);
		//adding main panel to JFrame
		
		GridLayout frameManager = new GridLayout(0, 1);
		//Instantiating Grid layout manager one column many rows
		signUpPanel.setLayout(frameManager);
		//Setting mainPanel to frame

		JLabel details = new JLabel("Please register your details below", SwingConstants.CENTER);
		//Label
		signUpPanel.add(details);
		//Adding label to panels
		
		JPanel namePanel = new JPanel();
		JLabel nameLabel = new JLabel("Full name");
		//Name Label
		nameTF = new JTextField(20);
		//Name Textfield
		namePanel.add(nameLabel);
		namePanel.add(nameTF);
		signUpPanel.add(namePanel);
		//Adding content to panel

		JPanel mobilePanel = new JPanel();
		JLabel mobileLabel = new JLabel("Phone number");
		//Phone number Label
		mobileTF = new JTextField(20);
		//Phone number Textfield
		mobilePanel.add(mobileLabel);
		mobilePanel.add(mobileTF);
		signUpPanel.add(mobilePanel);
		//Adding content to panel

		JPanel emailPanel = new JPanel();
		JLabel emailLabel = new JLabel("Email address");
		//Email Label
		emailTF = new JTextField(20);
		//Email TextField
		emailPanel.add(emailLabel);
		emailPanel.add(emailTF);
		signUpPanel.add(emailPanel);
		//Adding content to panel

		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("Password");
		//Password Label
		passwordTF = new JPasswordField(20);
		//Password Textfield
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordTF);
		signUpPanel.add(passwordPanel);
		//Adding content to panel

		JPanel buttonPanel = new JPanel();
		JButton cancel = new JButton("Cancel");
		//Cancel button
		JButton ok = new JButton("ok");
		//ok button
		cancel.addActionListener(controller);
		cancel.setActionCommand("cancelSign");
		ok.addActionListener(controller);
		ok.setActionCommand("Sign");
		buttonPanel.add(cancel);
		buttonPanel.add(ok);
		signUpPanel.add(buttonPanel);
		//Adding content to panel

		signUpPanel.validate();
		signUpPanel.repaint();

	}
	
	/** Main window customer logged in */
	public void mainCustomer(String[][] data) {
		
		String[] columns = {"Booking ID", "Barber", "Location", "Date", "time"};
		//Array for column names in JTable
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		//Set window in center of the screen
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Welcome");
		//Dispose frame on close
		
		BorderLayout grid = new BorderLayout();
		//Border Layout Manager
		mainPanel.setLayout(grid);
		//Setting Layout to panel
		JPanel topPanel = new JPanel();
		JPanel leftTop = new JPanel();
		
		TitledBorder options = new TitledBorder("Choose an option");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);
		
		selectBox = new JComboBox(selectOptions);
		
		JButton select = new JButton("Select");
		select.setActionCommand("selectmain");
		select.addActionListener(controller);
		
		leftTop.setBorder(options);
		
		leftTop.add(selectBox);
		leftTop.add(select);
		
		JPanel leftBottom = new JPanel();
		
		JButton logout = new JButton("Logg out");
		logout.setActionCommand("logoutmain");
		logout.addActionListener(controller);
		
		leftBottom.add(logout);
		
		//Adding both left panels
		topPanel.add(leftTop);
		topPanel.add(leftBottom);
		
		JPanel bottomPanel = new JPanel();
		
		TitledBorder booked = new TitledBorder("Booked");
		//Instantiating a border
		booked.setTitleJustification(TitledBorder.CENTER);
		booked.setTitlePosition(TitledBorder.TOP);
		
		JTable bookTable = new JTable(data, columns);
		JScrollPane scroll = new JScrollPane(bookTable);
		
		bottomPanel.setBorder(booked);
		//setting up border
		bottomPanel.add(scroll);
		//Adding table to panel
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		//Adding panel placed in top of main panel
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		//Adding panel placed in center of main panel
		
		this.add(mainPanel);
		//Add main panel to JFrame
		
		this.validate();
		this.repaint();
		
		
	}
	/** Cancel a booking */
	public void cancelBooking(String[][] data) {
		
		String[] columns = {"Booking ID", "Barber", "Location", "Date", "time"};
		//Array for column names in JTable
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Current Bookings");
		
		BorderLayout grid = new BorderLayout(0,1);
		//Border Layout Manager
		cancelBooking.setLayout(grid);
		//Setting layout to panel
		
		TitledBorder options = new TitledBorder("Select slot to cancel");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);
		//setting up border to main panel
		cancelBooking.setBorder(options);
		
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel cancelPanel = new JPanel();
		//Instantiating JPanels
		
		JLabel mylabel = new JLabel("Select row on the table to cancel");
		
		JButton cancel = new JButton("Cancel appointment");
		//Instantiating cancel button
		cancel.setActionCommand("cancelappointment");
		cancel.addActionListener(controller);
		
		cancelPanel.add(mylabel);
		cancelPanel.add(cancel);
		
		JPanel backPanel = new JPanel();
		JButton goback = new JButton("Go back");
		//Instantiating go back button
		goback.setActionCommand("goback");
		goback.addActionListener(controller);
		backPanel.add(goback);
		
		leftPanel.add(cancelPanel);
		leftPanel.add(backPanel);
		
				
		cancelTable = new JTable(data, columns);
		JScrollPane scroll = new JScrollPane(cancelTable);
		//Instantiating table
		
		rightPanel.add(scroll);
		
		cancelBooking.add(leftPanel, BorderLayout.NORTH);
		//Adding Panel positioned on top
		cancelBooking.add(rightPanel, BorderLayout.CENTER);
		//Adding Panel positioned in center
		
		this.add(cancelBooking);
		this.pack();
		this.validate();
		this.repaint();
		
	}
	

	/** Make a new Booking */
	public void makeBooking(String[][] data) {
		
		String[] columns = {"ID", "Barber", "Location", "Date", "time"};
		//Declaring array variables for table column
		String[] radioButtons = {"Name", "Location"};
		//Declaring array variables for radio buttons
		
		this.setVisible(true);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("New Booking");
		
		BorderLayout grid = new BorderLayout();
		//Instanciating Border layout manager
		makeBooking.setLayout(grid);
		//Setting Border Layout to main panel
		
		JPanel topPanel = new JPanel();
		JPanel topTop = new JPanel();
		
		TitledBorder options = new TitledBorder("Search Barber");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);
		//setting up border

		JLabel barber = new JLabel("Barber");
		//Instantiating Barber to label
		searchBarber = new JTextField(20);
		//Textfield for search barber
		
		name = new JRadioButton(radioButtons[0]); 
		//Instantiating radio button with name option
		name.setSelected(true);
		location = new JRadioButton(radioButtons[1]);
		//Instantiating radio button with location option
		ButtonGroup group = new ButtonGroup(); 
		JPanel radioPanel = new JPanel();
		
		
		group.add(name); 
		group.add(location); 
		
		radioPanel.add(name);
		radioPanel.add(location);
		
		JButton select = new JButton("Select");
		//Instantiating Select button
		select.setActionCommand("selectbooking");
		select.addActionListener(controller);
		
		topTop.setBorder(options);
		//Setting border to main panel
		
		topTop.add(barber);
		topTop.add(searchBarber);
		topTop.add(radioPanel);
		topTop.add(select);
		
		JPanel topBottom = new JPanel();
		
		JButton logout = new JButton("Logg out");
		//Instantiating logg out button
		logout.setActionCommand("logoutmain");
		logout.addActionListener(controller);
		
		JButton goback = new JButton("go back");
		//Instantiating go back button
		goback.setActionCommand("goback");
		goback.addActionListener(controller);
		
		
		topBottom.add(logout);
		topBottom.add(goback);
		
		//Adding both left panels
		topPanel.add(topTop);
		topPanel.add(topBottom);
		
		JPanel mainbottomPanel = new JPanel(new BorderLayout());
		JPanel bottomTop = new JPanel();
		JPanel bottomBottom = new JPanel();
		
		TitledBorder booked = new TitledBorder("Available slots");
		//Instantiating a border
		booked.setTitleJustification(TitledBorder.CENTER);
		booked.setTitlePosition(TitledBorder.TOP);
		
		bookTable = new JTable(data, columns);
		//Instantiating a table
		JScrollPane scroll = new JScrollPane(bookTable);
	
		JButton addBooking = new JButton("Make Booking");
		//Instantiating booking button
		addBooking.addActionListener(controller);
		addBooking.setActionCommand("makebooking");
		
		
		mainbottomPanel.setBorder(booked);
		
		bottomBottom.add(scroll);
		bottomTop.add(addBooking);
		
		
		mainbottomPanel.add(bottomTop, BorderLayout.NORTH);
		//setting up border in top
		mainbottomPanel.add(bottomBottom, BorderLayout.CENTER);
		//setting up border in center
		
		makeBooking.add(topPanel, BorderLayout.NORTH);
		//setting up border in top
		makeBooking.add(mainbottomPanel, BorderLayout.CENTER);
		//setting up border in center
		
		this.add(makeBooking);
		//Adding main panel to JFrame
		this.pack();
		this.validate();
		this.repaint();
	}
	
	/** Clear Sign up panel */
public void clearSignUp() {
		
		signUpPanel.removeAll();
		this.pack();
	}

/** Clear main panel */
public void clearMain() {
		
		mainPanel.removeAll();
		this.pack();
	}

/** Clear Booking panel */
public void clearBooking() {

		makeBooking.removeAll();
		this.pack();
	}

/** Clear cancel booking panel */
public void clearCancelBooking() {

	cancelBooking.removeAll();
	this.pack();
}

/** get option from main */
	public Integer getSelectedRow() {
		Integer row = bookTable.getSelectedRow();
		System.out.println("row is "+row);
		return row;
}
	/** get option from main */
	public Integer getSelectedRowCancel() {
		Integer row = cancelTable.getSelectedRow();
		
		return row;
}


	/** get option from main */
	public String getOptionMain() {
	
		return (selectOptions[selectBox.getSelectedIndex()]);
	}

	/** get radio button */
	public String getRadioButton() {
		if (name.isSelected()) {
			return name.getText();
		} else if(location.isSelected()) {
			return location.getText();
		}
		return "null";
	}
	
	/** get option from make booking */
	public String getOptionMakeBooking() {

		return(selectBooking[selectBox.getSelectedIndex()]);
	}
	
	/** successful register */
	public int successfulCustomer() {
		// Just pop-up message
		
		String[] options = { "Log out", "Book Appointment" };
		int m = JOptionPane.showOptionDialog(this, "You have been successfully resgistered!", "Account",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
				options, // the titles of the button
				options[1]); // default button title

		return m;
	}
	/** successful booking*/
	public void successfulBooking() {
		// Just pop-up message
		
		//Just pop-up message
		JOptionPane.showMessageDialog(null, "Succesfull Book, now waiting for approval");
	}
	
	/** booking cancelled*/
	public void popupcancelled() {
		// Just pop-up message
		
		//Just pop-up message
		JOptionPane.showMessageDialog(null, "This Booking has been cancelled");
	}
	
	/** unsuccessful register */
	public void unSuccessfulCustomer() {
		// Just pop-up message
		
		String[] options = { "Try Again", "Cancel"};
		int m = JOptionPane.showOptionDialog(this, "Fill in your details correctly", "Registration",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
				options, // the titles of the button
				options[1]); // default button title
		System.out.println(m);
		if(m == 0) {
			
			nameTF.setText("");
			mobileTF.setText("");
			emailTF.setText("");
			passwordTF.setText("");

		}else {
			
			this.dispose();
		}
	}
	
	/**Get search**/
	public String getSearch() {
		
		return "'%"+searchBarber.getText()+"%'";
	}
	
	/**Get name**/
	public String getName() {
		
		String name=nameTF.getText();
		if(name.equals("")) {
			name="null";
		}else {
			name = "'"+name+"'";
		}	
		return name;
	}
	
	/**Get phone**/
	public String getPhone() {
		
		String phone=mobileTF.getText();
		if(phone.equals("")) {
			phone="null";
		}else {
			phone= "'"+phone+"'";
		}
		return phone;
	}
	
	/**Get email**/
	public String getEmail() {
		
		String email=emailTF.getText();
		if(email.equals("")) {
			email="null";
		}else {
			email = "'"+email+"'";
		}
		return email;
	}
	
	/**Get password**/
	public String getPassword() {
		
		String hashed= passwordTF.getText();
		if(hashed.equals("")) {
			return hashed="null";
		}else {
		hashed = BCrypt.hashpw(passwordTF.getText(), BCrypt.gensalt());
			 return "'"+hashed+"'";
		}
	}
}
