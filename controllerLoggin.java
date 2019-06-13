package barber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controllerLoggin implements ActionListener {

	viewLoggin viewLogg;
	//Global object of the Viewloggin class
	JDBC reading = new JDBC();
	//Global object of  the database class
	ModelH model = new ModelH();
	//Global object of the model class
	
	/**Default Constructor**/
	public controllerLoggin() {

		viewLogg = new viewLoggin(this);
		//Instance of the viiewlogg class
	}

	@Override
	/**Action performed from viewlogging class**/
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		/**Action performed from loggin button**/
		if (arg0.getActionCommand().equals("login")) {

			String[][] data;

			reading.reading(model.loginCustomer(viewLogg.getEmail()), 3);
			//reading from database comparing email with customers
			data = reading.getDataLoggin();

			if (data[0][0] != null) {
				// If data is not null is a customer

				if (viewLogg.getPassword(data[0][2])) {
					// If password matches with input loggin as a customer
					
					controllerCustomer customer = new controllerCustomer(data[0][0]);
					//Instance of controller customer class
					
					viewLogg.clearPassword();
					viewLogg.clearEmail();
					reading.arraytoNull();
					//clearing panel and arrays to be re-used
				} else {
					
					viewLogg.clearPassword();
					viewLogg.clearPanel();
					viewLogg.loggin();
					viewLogg.reLog();
					reading.arraytoNull();
					//clearing panel and arrays to be re-used
				}
			} else {

				reading.reading(model.loginBarberApproved(viewLogg.getEmail()), 3);
				//reading from database comparing email with barbers approved
				data = reading.getDataLoggin();
				//Storing information in an array
				
				if (data[0][0] != null) {
					// If data is not null is a barber approved
					
					if (viewLogg.getPassword(data[0][2])) {
						// If password matches with input loggin as a barber
						
						controllerBarber barber = new controllerBarber(data[0][0]);
						//Instance of controller barber class
						
						viewLogg.clearEmail();
						viewLogg.clearPassword();
						reading.arraytoNull();
						//clearing panel and arrays to be re-used

					} else {
						// The barber introduce an Incorrect password
						
						viewLogg.clearPassword();
						viewLogg.clearPanel();
						viewLogg.loggin();
						viewLogg.reLog();
						reading.arraytoNull();
						//clearing panel and arrays to be re-used
					}

				} else if (data[0][0] == null) {
					
					reading.reading(model.loginBarberPending(viewLogg.getEmail()), 3);
					//reading from database comparing email with barbers pending
					data = reading.getDataLoggin();
					//Storing information in an array
					
					if (data[0][0] != null) {
						// If data is not null is a barber pending
						
						if (viewLogg.getPassword(data[0][2])) {
							// If password matches with input is a pending barber
							
							viewLogg.clearEmail();
							viewLogg.clearPassword();
							viewLogg.clearPanel();
							viewLogg.loggin();
							viewLogg.approvation();
							reading.arraytoNull();
							//clearing panel and arrays to be re-used
							
						} else {
							// The barber introduce an Incorrect password
							
							viewLogg.clearEmail();
							viewLogg.clearPassword();
							viewLogg.clearPanel();
							viewLogg.loggin();
							viewLogg.reLog();
							reading.arraytoNull();
							//clearing panel and arrays to be re-used
						}

					} else {
						// The input does not match any email or password
						
						viewLogg.clearPassword();
						viewLogg.clearPanel();
						viewLogg.loggin();
						viewLogg.reLog();
						reading.arraytoNull();
						//clearing panel and arrays to be re-used

					}
				}
			}
			/**Action performed signup button**/
		} else if (arg0.getActionCommand().equals("signup")) {

			if (viewLogg.signUp().equals("Customer")) {
				//customer wants to sign up
				
				controllerCustomer customer = new controllerCustomer();
				//Instance of controller customer class
			} else {
				//barber wants to sign up
				controllerBarber barber = new controllerBarber();
				//Instance of controller barber class
			}
		}
	}
}
