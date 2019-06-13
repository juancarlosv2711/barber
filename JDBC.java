package barber;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class JDBC {

	private String[][] dataLoggin = new String[100][100];
	private String[][] dataLoggin2 = new String[100][100];
	// Global variable where Data from database is stored

	/** Getter for dataLoggin[][] **/
	public String[][] getDataLoggin() {
		return this.dataLoggin;

	}

	/** Getter for dataLoggin2[][] **/
	public String[][] getDataLoggin2() {
		return this.dataLoggin2;

	}
	/** Setting dataLoggin[][]  null**/
	public void arraytoNull() {
		for (int row = 0; row < 100; row++) {
			for (int col = 0; col < 100; col++) {
				dataLoggin[row][col] = null;

			}
		}
	}
	
	/** Setting dataLoggin[][] to empty **/
	public void clearingArray() {
		for (int row = 0; row < 100; row++) {
			for (int col = 0; col < 100; col++) {
				dataLoggin[row][col] = "";

			}
		}
	}

	/** Setting dataLoggin2[][] to empty **/
	public void clearingArray2() {
		for (int row = 0; row < 100; row++) {
			for (int col = 0; col < 100; col++) {
				dataLoggin2[row][col] = "";
			}
		}
	}

	/** Getter for id selected **/
	public String getidtable(int row) {

		return dataLoggin[row][0];
	}

	/** Database reading **/
	public void reading(String query, int x) {

		try {

			// Load the database driver
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			String dbServer = "jdbc:mysql://localhost:3306/barbers";
			String user = "root";
			String password = "jc27vl11";

			// Get a connection to the database
			Connection conn = DriverManager.getConnection(dbServer, user, password);

			// Get a statement from the connection
			Statement stmt = conn.createStatement();

			// Execute the query
			ResultSet rs = stmt.executeQuery(query);

			int rows = 0;
			int colmnIndex = 0;

			while (rs.next()) {

				for (int column = 0; column < x; column++) {
					colmnIndex = column + 1;
					dataLoggin[rows][column] = rs.getString(colmnIndex);
					System.out.println(rs.getString(colmnIndex));
				}
				colmnIndex = 0;
				rows = rows + 1;
			}

			// Close the result set, statement and the connection
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
			while (se != null) {
				System.out.println("State  : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error  : " + se.getErrorCode());

				se = se.getNextException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/** Database reading2 **/
	public void reading2(String query, int x) {

		try {

			// Load the database driver
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			String dbServer = "jdbc:mysql://localhost:3306/barbers";
			String user = "root";
			String password = "jc27vl11";

			// Get a connection to the database
			Connection conn = DriverManager.getConnection(dbServer, user, password);

			// Get a statement from the connection
			Statement stmt = conn.createStatement();

			// Execute the query
			ResultSet rs = stmt.executeQuery(query);

			// //Check how many rows are and get last row
			// rs.last();
			int rows = 0;
			int colmnIndex = 0;
			// //Going back to before last row
			// rs.beforeFirst();
			//

			while (rs.next()) {

				for (int column = 0; column < x; column++) {
					colmnIndex = column + 1;
					dataLoggin2[rows][column] = rs.getString(colmnIndex);
					}
				colmnIndex = 0;
				rows = rows + 1;
			}

			// Close the result set, statement and the connection
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
			while (se != null) {
				System.out.println("State  : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error  : " + se.getErrorCode());

				se = se.getNextException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** Database writting **/
	public int writting(String query) {
		try {

			// Load the database in the driver
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			String dbServer = "jdbc:mysql://localhost:3306/barbers";
			String user = "root";
			String password = "jc27vl11";

			// Get a connection with the database
			Connection conn = DriverManager.getConnection(dbServer, user, password);

			// Create Statement
			PreparedStatement preparedstmt = conn.prepareStatement(query);

			// Execute the statement
			preparedstmt.execute();

			// Close the result set, statement and connection

			conn.close();

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		return 1;
	}

}
