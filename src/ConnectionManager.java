package edu.unsw.comp9321.assign2;

import org.apache.derby.jdbc.ClientDriver;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionManager {
	Connection connection;
	
	public ConnectionManager() {
		// initialise a connection
		/*try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		}
		catch (Exception e) {
			System.out.println("Driver not found");
		}*/
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connection = DriverManager.getConnection("jdbc:derby://127.0.0.1:1527/9321;create=true", 
					"user", "123");
		}
		catch (Exception e) {
			System.out.println("ConnectionManger: Connection failed");
			e.printStackTrace();
		}
		//connection = null;
		System.out.println("Connection established");
	}
	
	public Connection getConnection() {
		return connection;
	}
}
