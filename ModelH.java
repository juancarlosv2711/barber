package barber;

public class ModelH {
	
	
	/**Statement login Customer**/
	public String loginCustomer(String email) {
		
		
		return "SELECT c_id, email, pswd FROM customer WHERE email = " + email
		+";";
	}

	/**Statement login Barber approved**/
	public String loginBarberApproved(String email) {
		
		
		return "SELECT b_id, email, pswd FROM barber WHERE email = " + email
		+ " and b_status = 'approved';";
	}
	
	/**Statement login Barber pending**/
	public String loginBarberPending(String email) {
		
		
		return "SELECT b_id, email, pswd FROM barber WHERE email = " + email
		+ " and b_status = 'pending';";
	}

}