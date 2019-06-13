package barber;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.xml.bind.DatatypeConverter;

/** This is the View class with JFrame extended**/
public class viewLoggin extends JFrame {

	// Private object of controller is instantiated to get Action Listener in this
	// class
	private controllerLoggin controller;
	// TextFields global so other methods can access it
	private JTextField emailTF;
	// private JTextField passwordTF;
	private JPasswordField passwordTF;
	private JPanel mainPanel = new JPanel();

	/**Action listener comes from controller class in default constructor**/
	public viewLoggin(controllerLoggin controllerOut) {

		this.controller = controllerOut;
		// Getting controller from parameter and storing it in a global variable
		
		setUp();
		loggin();
		// Calling methods
	}

	/**Getter email**/
	public String getEmail() {

		String getEmail = emailTF.getText();
		//Getting email from textfield
		
		if (getEmail.equals("")) {
			//Setting email to null if it is empty
			return "null";
		} else {
			//returning email
			return "'" + getEmail + "'";
		}

	}

	/**Get password**/
	public Boolean getPassword(String hashed) {

		boolean isCorrect = false;
		//Setting a boolean variable
		if (BCrypt.checkpw(passwordTF.getText(), hashed)) {
			//Comparing input user from textfield and comparing to stored password coming from parameter returning true if it matches
			return isCorrect = true;
			
		} else {
			//Returning false if it does not match
			return isCorrect = false;
		}
	}

	/**Clearing password Textfield**/
	public void clearPassword() {
		this.passwordTF.setText("");
	}

	/**Clearing email Textfield**/
	public void clearEmail() {
		this.emailTF.setText("");
	}

	/** Removes panel**/
	public void clearPanel() {

		mainPanel.removeAll();
		this.revalidate();
		this.repaint();
	}

	/**Setting up panel**/
	public void setUp() {

		this.setVisible(true);
		this.setSize(400, 200);
		this.setTitle("BarberBlade");
		this.setLocationRelativeTo(null);
		//Set window in center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dispose frame on close
	}

	/** The method for log in window */
	public void loggin() {

		this.add(mainPanel);
		//adding main panel to JFrame
		
		GridBagLayout frameManager = new GridBagLayout();
		//Instanciating Grid layout manager
		mainPanel.setLayout(frameManager);
		//Setting mainPanel to frame
		GridBagConstraints c = new GridBagConstraints();

		TitledBorder options = new TitledBorder("Welcome");
		//Instantiating a border
		options.setTitleJustification(TitledBorder.CENTER);
		options.setTitlePosition(TitledBorder.TOP);
		//setting up border
		mainPanel.setBorder(options);
		//Setting border to main panel
		
		c.gridx = 0;
		c.gridy = 0;
		JLabel emailLabel = new JLabel("Email");
		mainPanel.add(emailLabel, c);
		//Adding a email Label to Panel

		c.gridx = 1;
		c.gridy = 0;
		emailTF = new JTextField(20);
		mainPanel.add(emailTF, c);
		//Adding a email textfield to Panel

		c.gridx = 0;
		c.gridy = 1;
		JLabel passwordLabel = new JLabel("Password");
		mainPanel.add(passwordLabel, c);
		//Adding a password Label to Panel

		c.gridx = 1;
		c.gridy = 1;
		passwordTF = new JPasswordField(20);
		passwordTF.setEchoChar('*');
		mainPanel.add(passwordTF, c);
		//Adding a password textfield to Panel

		c.gridx = 1;
		c.gridy = 2;
		JPanel buttons = new JPanel();
		JButton login = new JButton("Login");
		login.addActionListener(controller);
		login.setActionCommand("login");
		JButton signUp = new JButton("Sign up");
		signUp.addActionListener(controller);
		signUp.setActionCommand("signup");
		buttons.add(login);
		buttons.add(signUp);
		mainPanel.add(buttons, c);
		//Adding buttons to Panel

		mainPanel.validate();
		mainPanel.repaint();

	}

	/** Checking if user would like to register as customer/barber**/
	public String signUp() {

		String[] posibilities = { "Customer", "Barber" };
		//Variable array showing two options
		
		String s = (String) JOptionPane.showInputDialog(this, "What type of Account would you like to register",
				"Sign Up", JOptionPane.PLAIN_MESSAGE, null, // do not use a custom Icon
				posibilities, // the titles of the button
				posibilities[0]); // default button title
		return s;
	}

	/** Enter details again */
	public void reLog() {

		JOptionPane.showMessageDialog(null, "Please, enter valid email or password!");
		//pop-up window asking to enter valid inputs
	}

	public void approvation() {

		JOptionPane.showMessageDialog(null, "You need to be approved by a manager!");
		//pop-up window displaying the need of a manager approval
	}
}
