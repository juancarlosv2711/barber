package barber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controllerBarber implements ActionListener {

	viewBarber view;
	//Global object of the Viewloggin class
	JDBC database = new JDBC();
	//Global object of  the database class
	private String user;
	//Global variable where user id is stored

	/**Default Constructor**/
	public controllerBarber() {
		//This method performs the sign up customer
		
		view = new viewBarber(this);
		view.signupBarber();
		//Instance of the viewbarber class

	}

	/**Default Constructor with one parameter**/
	public controllerBarber(String currentUser) {
		//This method takes as parameter the user ID
		
		view = new viewBarber(this);
		//Controller is passed through parameter to be used in viewbarber class
		
		this.user = currentUser;
		//Id user is stored in global variable
		
		database.reading("SELECT av_id, indate, time FROM availability WHERE b_id = '" + user + "'", 3);
		//Query for availabilities already set by barber
		
		view.availabilityWindow(database.getDataLoggin());
		//Main window that displays availabilities
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		/**Action performed from signbarber button**/
		if (e.getActionCommand().equals("SignBarber")) {
			
			int validation = database
					.writting("INSERT INTO barber (name, location, phone, email, pswd, b_status) VALUES ("
							+ view.getName() + ", " + view.getLocations() + ", " + view.getPhone() + ", "
							+ view.getEmail() + ", " + view.getPassword() + ", 'pending');");
			//Getting input from user and inserting it into the database
			
			if (validation == 0) {
				// Error writting in database
				
				view.unSuccessfulBarber();
				//pop-up message displaying unsuccessful barber
				
			} else {
				//pop-up message displaying succesfull barber
				view.popupapproval();
				view.dispose();
				//closing window
			}
		/**Action performed from cancelbarber button**/
		} else if (e.getActionCommand().equals("cancelBarber")) {
			//cancels the sign up and goes back to loggin window
			
			view.dispose();
			view.clearSignup();
			//clear panel and close window

		/**Action performed from date button**/
		} else if (e.getActionCommand().equals("date")) {
			//Sets barber availability

			view.clearAvailability();
			//Refresh the main window
			database.writting("INSERT INTO availability (b_id, indate, time, a_status) VALUES ('" + user + "', '"
					+ view.getDate() + "', '" + view.getTime() + "', 'free');");
			//Writting dates and time to set new availability slots in database
			database.reading("SELECT av_id, indate, time FROM availability WHERE b_id = '" + user + "'", 3);
			//Reading availability slots from database
			view.availabilityWindow(database.getDataLoggin());
			//Displaying window with databse
			
			/**Action performed from events button**/
		} else if (e.getActionCommand().equals("events")) {
			//This window shows the Coming events and the events that need status update
			
			view.clearAvailability();
			//Close previous panel to display new one
			database.clearingArray();
			//Clearing array from previous enquire
			database.reading(
					"SELECT booking.book_p, customer.name, availability.indate, availability.time, booking.bo_status FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN customer ON booking.c_id=customer.c_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.b_id = '"
							+ user + "' and booking.bo_status = 'pending';",
					5);
			//Getting information from database to display the events that needs status update
			database.reading2(
					"SELECT booking.book_p, customer.name, availability.indate, availability.time, booking.bo_status FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN customer ON booking.c_id=customer.c_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.b_id = '"
							+ user + "' and booking.bo_status = 'approved';",
					5);
			view.events(database.getDataLoggin(), database.getDataLoggin2());
			//Passing the variables into the method

		} else if (e.getActionCommand().equals("home")) {
			//This window displays the home main window
			
			view.clearevents();
			database.clearingArray();
			database.clearingArray2();
			//Getting arrays ready for main window

			database.reading("SELECT av_id, indate, time FROM availability WHERE b_id = '" + user + "'", 3);
			//Getting information from database to display the main window
			view.availabilityWindow(database.getDataLoggin());
			//Passing the variables into the method
			
		} else if (e.getActionCommand().equals("logout")) {
			//This Action close the windows and sends user back to logout

			view.clearAvailability();
			database.clearingArray();
			database.clearingArray2();
			//Clearing arrays
			view.dispose();
			//Dispose

		} else if (e.getActionCommand().equals("cancelbooking")) {
			//This Action cancel bookings

			database.writting("UPDATE booking SET bo_status='cancelled' WHERE book_p='"
					+ database.getidtable(view.getSelectedRow()) + "';");
			//Writing updates in database
			view.clearevents();
			database.clearingArray();
			database.clearingArray2();
			//Getting arrays ready for main window

			database.reading(
					"SELECT booking.book_p, customer.name, availability.indate, availability.time, booking.bo_status FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN customer ON booking.c_id=customer.c_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.b_id = '"
							+ user + "' and booking.bo_status = 'pending';",
					5);
			//Getting information from database "pending bookings" to display the events window
			database.reading2(
					"SELECT booking.book_p, customer.name, availability.indate, availability.time, booking.bo_status FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN customer ON booking.c_id=customer.c_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.b_id = '"
							+ user + "' and booking.bo_status = 'approved';",
					5);
			//Getting information from database "approved" to display the events window
			view.events(database.getDataLoggin(), database.getDataLoggin2());
			//Passing the variables into the method
			
		} else if (e.getActionCommand().equals("approve")) {
			//This Action approves bookings
			database.writting("UPDATE booking SET bo_status='approved' WHERE book_p='"
					+ database.getidtable(view.getSelectedRow()) + "';");
			//Writing updates in database
			
			view.clearevents();
			database.clearingArray();
			database.clearingArray2();
			//Getting arrays ready for main window
			
			database.reading(
					"SELECT booking.book_p, customer.name, availability.indate, availability.time, booking.bo_status FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN customer ON booking.c_id=customer.c_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.b_id = '"
							+ user + "' and booking.bo_status = 'pending';",
					5);
			//Getting information from database "pending bookings" to display the events window
			database.reading2(
					"SELECT booking.book_p, customer.name, availability.indate, availability.time, booking.bo_status FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN customer ON booking.c_id=customer.c_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.b_id = '"
							+ user + "' and booking.bo_status = 'approved';",
					5);
			//Getting information from database "approved" to display the events window

			view.events(database.getDataLoggin(), database.getDataLoggin2());
			//Passing the variables into the method
		}
	}

}
