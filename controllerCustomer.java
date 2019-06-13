package barber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controllerCustomer implements ActionListener {

	viewCustomer view;
	// Global object of the ViewCustomer class
	JDBC database = new JDBC();
	// Global instance of the database class
	private String user;
	// Global variable where user id is stored

	/** Default Constructor **/
	public controllerCustomer() {
		// This method performs the sign up customer

		view = new viewCustomer(this);
		view.signupCustomer();
		// Instance of the viewCustomer class

	}

	/** Default Constructor one parameters **/
	public controllerCustomer(String currentUser) {
		// This method takes as parameter the user ID

		view = new viewCustomer(this);
		// Controller is passed through parameter to be used in viewCustomer class

		this.user = currentUser;
		// Id user is stored in global variable comming from parameter

		database.reading(
				"SELECT booking.book_p, barber.name, barber.location, availability.indate, availability.time FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE booking.c_id = '"
						+ user + "' and booking.bo_status = 'approved';",
				5);
		// Query for bookings approved by barber

		view.mainCustomer(database.getDataLoggin());
		// Main window that displays bookings
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		/** Action performed from signcustomer button **/
		if (e.getActionCommand().equals("Sign")) {

			int validation = database
					.writting("INSERT INTO customer (name, phone, email, pswd) VALUES (" + view.getName() + ", "
							+ view.getPhone() + ", " + view.getEmail() + ", " + view.getPassword() + ");");
			// Getting input from user and inserting it into the database

			if (validation == 0) {
				// Error writing in database

				view.unSuccessfulCustomer();
				// pop-up message displaying unsuccessful customer

				database.clearingArray();
				// Clearing array from previous enquire
			} else {
				// pop-up message displaying successful customer
				int validation2 = view.successfulCustomer();

				if (validation2 == 0) {
					// Option selected to log out after registration
					view.dispose();
					database.clearingArray();
					view.clearSignUp();

				} else {
					// Option selected to book appointment after registration
					view.dispose();
					view.clearSignUp();
					// Remove Signup panel
					database.clearingArray();
					// Clearing array from previous enquire
					database.reading(
							"SELECT booking.book_p, barber.name, barber.location, availability.indate, availability.time FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE booking.c_id = '"
									+ user + "' and booking.bo_status = 'approved';",
							5);
					// Reading approved booking slots from database
					view.mainCustomer(database.getDataLoggin());
					// Displaying main window with database
				}
			}

			/** Action performed cancel the sign in button **/
		} else if (e.getActionCommand().equals("cancelSign")) {

			view.dispose();
			//Dispose the sign up customer window and back to log in

			/** Action performed Select main button **/
		} else if (e.getActionCommand().equals("selectmain")) {
			//Select main displays 3 options to the customer
			
			if (view.getOptionMain().equals("Make a new Booking")) {
				//Option selected Make a new booking
				
				database.clearingArray();
				// Clearing array from previous enquire
				view.clearMain();
				// Remove Main panel
				String[][] empty = new String[0][0];
				view.makeBooking(empty);
				//First time Booking is displayed has no data
				
			} else if (view.getOptionMain().equals("Cancel a Booking")) {
				//Option selected Cancel a booking
				
				view.clearMain();
				// Remove Main panel
				database.clearingArray();
				// Clearing array from previous enquire
				database.reading(
						"SELECT booking.book_p, barber.name, barber.location, availability.indate, availability.time FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE booking.c_id = '"
								+ user + "' and booking.bo_status = 'approved';",
						5);
				// Reading approved booking slots from database
				view.cancelBooking(database.getDataLoggin());
				// Displaying booking window options to be cancelled
				
			} else if (view.getOptionMain().equals("Make a Complaint")) {
				//Option selected Make a Complaint
				System.out.println("Make a Complaint");
			}
			
			/** Action performed Select booking button **/
		} else if (e.getActionCommand().equals("selectbooking")) {
			//Option to select a booking
			
			System.out.println(view.getSelectedRow());
			if (view.getRadioButton().equals("Name")) {
				//When the user search barber by name
				
				database.clearingArray();
				//Clearing array from previous enquire
				database.reading(
						"SELECT availability.av_id, barber.name, barber.location, availability.indate, availability.time FROM availability INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.name LIKE "
								+ view.getSearch() + " and availability.a_status='free';",
						5);
				//Getting information from database to display the events that needs status update
				view.clearBooking();
				// Remove Booking panel
				view.makeBooking(database.getDataLoggin());
				//Passing the variables into the method
			
			} else if (view.getRadioButton().equals("Location")) {
				//When the user search barber by location
				
				database.clearingArray();
				view.clearBooking();
				// Remove Booking panel
				database.clearingArray();
				//Clearing arrays
				database.reading(
						"SELECT availability.av_id, barber.name, barber.location, availability.indate, availability.time FROM availability INNER JOIN barber ON availability.b_id=barber.b_id WHERE barber.location LIKE "
								+ view.getSearch() + " and availability.a_status='free';",
						5);
				//Getting information from database "pending bookings" to display the events window
				view.makeBooking(database.getDataLoggin());
				//Passing the variables into the method
			}
			
			/** Action performed Select booking button **/
		} else if (e.getActionCommand().equals("logoutmain")) {
			
			view.clearBooking();
			// Remove Booking panel
			view.clearMain();
			// Remove Main panel
			view.dispose();
			// Close window
			
			/** Action performed make booking button **/
		} else if (e.getActionCommand().equals("makebooking")) {

			database.writting("INSERT INTO booking (av_id, c_id, bo_status) VALUES ('"
					+ database.getidtable(view.getSelectedRow()) + "', '" + user + "','pending');");
			//Writing into database to insert a booking
			database.writting("UPDATE availability SET a_status='booked' WHERE av_id='"
					+ database.getidtable(view.getSelectedRow()) + "';");
			//Update availability table status to booked after customer has booked
			view.successfulBooking();
			//Pop up window with successful booking
			view.clearBooking();
			// Remove Booking panel
			database.clearingArray();
			//Clearing arrays
			database.reading(
					"SELECT booking.book_p, barber.name, barber.location, availability.indate, availability.time FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE booking.c_id = '"
							+ user + "' and booking.bo_status = 'approved';",
					5);
			//Getting information from database "approved bookings" to display the events window
			view.mainCustomer(database.getDataLoggin());
			//Passing the variables into the main method

			/** Action performed cancel appointment button **/
		} else if (e.getActionCommand().equals("cancelappointment")) {

			if (view.getSelectedRowCancel() != -1) {
				//If a row has been selected
				
				database.writting("UPDATE booking SET bo_status='cancelled' WHERE book_p='"
						+ database.getidtable(view.getSelectedRowCancel()) + "';");
				//Update booking table status to booked after customer has booked
				view.popupcancelled();
				//Pop up cancelled
				view.clearCancelBooking();
				// Remove Booking panel
				database.clearingArray();
				//Clearing arrays
				database.reading(
						"SELECT booking.book_p, barber.name, barber.location, availability.indate, availability.time FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE booking.c_id = '"
								+ user + "' and booking.bo_status = 'approved';",
						5);
				//Getting information from database "approved bookings" to display the events window
				view.mainCustomer(database.getDataLoggin());
				//Passing the variables into the main method
			}
			
			/** Action performed go back button **/
		} else if (e.getActionCommand().equals("goback")) {
			
			view.clearBooking();
			// Remove Booking panel
			view.clearCancelBooking();
			// Remove Cancel Booking panel
			database.clearingArray();
			//Clearing arrays
			database.reading(
					"SELECT booking.book_p, barber.name, barber.location, availability.indate, availability.time FROM booking INNER JOIN  availability ON booking.av_id=availability.av_id INNER JOIN barber ON availability.b_id=barber.b_id WHERE booking.c_id = '"
							+ user + "' and booking.bo_status = 'approved';",
					5);
			//Getting information from database "approved bookings" to display the events window
			view.mainCustomer(database.getDataLoggin());
			//Passing the variables into the main method
		}
	}
}
