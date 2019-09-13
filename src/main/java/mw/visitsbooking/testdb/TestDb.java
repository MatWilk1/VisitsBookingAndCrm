package mw.visitsbooking.testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDb {

	public static void main(String[] args) {

		String jdbcURL = "jdbc:mysql://localhost:3306/visits-booking-and-crm?useSSL=false&amp;serverTimezone=UTC";
		String user = "springstudent";
		String pass = "springstudent";
		
		try {
			System.out.println("Connecting to database" + jdbcURL);
			
			Connection myConn = DriverManager.getConnection(jdbcURL, user, pass);
			
			System.out.println("OK!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}


