package edu.unsw.comp9321;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;

public class DbManager {
	String uName = null;
	String nName = null;
	ResultSet resultSet = null;
	Connection connection = null;
	
	/**
	 * Constructor initialises Connection
	 */
	public DbManager () {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		}
		catch (Exception e) {
			System.out.println("Driver not found");
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:derby://127.0.0.1:1527/9321;create=true", 
					"user", "123");
		}
		catch (Exception e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Register a new user in the Database
	 * @param uName - Username
	 * @param passwd - Users Password
	 * @return success or failure
	 */
	public boolean register(String uName, String passwd, String nName, String fName,
			String lName, String email, int birthyear, String address, String creditCard,
			String role) {
		
		String newUser = "INSERT INTO member " + 
				"(username, password, nickname, firstname, lastname, email, birthyear, address, creditcard, role)" +
				" VALUES ('" + uName + "', '" + passwd + "', '" + nName + "', '" + fName + "', '" + lName + 
				"', '" + email + "', '" + birthyear + "', '" + address + "', '" + creditCard + "', '" + 
				role + "')";
		
		insert(newUser);
		return true;
	}
	
	/**
	 * authenticates users already in Database
	 * @param uName - Username
	 * @param passwd - Users Password
	 * @return true if credentials found else false
	 */
	public boolean authenticate(String uName, String passwd) {
		String credentials = "SELECT * FROM member WHERE username = " +
				uName + " AND username = " + passwd;
		query(credentials);
		return true;
		
	}
	
	/**
	 * Adds Auction details to item and auction tables
	 * @param itemid - Items ID number
	 * @param title - Item name
	 * @param category - category of item
	 * @param picture - photo of item
	 * @param description - description of item
	 * @param postagedetails - postage method for delivery
	 * @param reserveprice - starting price
	 * @param bidincrement - minimum increment of bids
	 * @param closingtime - number hours after auction start
	 * @param username - User Id registering the auction
	 * @param startTime - Start time of the auction
	 * @return true if successfully added
	 */
	public boolean addAuction(int itemid, String title, String category, 
	        String picture, String description, String postagedetails, int reserveprice, 
	        int bidincrement, int closingtime, String username, String startTime) { // check how to represent time
		
		String item = "INSERT INTO item (itemid, title, category, picture, description, " + 
				"postagedetails, reserveprice, bidincrement, closingtime) VALUES (" + itemid + 
				"', '" + title + "', '" + category + "', '" + picture + "', '" + description + "', '" + 
				postagedetails + "', '" + reserveprice + "', '" + bidincrement + "', '" + 
				closingtime + "', '" + username + "', '" + startTime + ")";
		
		String auction = "INSERT INTO auction (int item, String username, time starttime)" +
				" VALUES (" + itemid + "', '" + username + "', '" + startTime + ")";
		
		insert(item);
		insert(auction);
		return true;
	}
	
	/**
	 * Submits bid to the Database
	 * @param s - String containing bid details
	 * @return true if bid successfully written to database
	 */
	public boolean submitBid(Date biddate, Time bidtime, String bidder, int item, int amount) {
		// may need to include checks bid is value or not duplicate
		String bid = "INSERT INTO bid (biddate, bidtime, bidder, item, amount) VALUES (" + biddate +
				"', '" + bidtime + "', '" + bidder + "', '" + item + "', '" + amount + ")";
		
		insert(bid);
		return true;
	}
	
	/**
	 * Performs Insert query stored in s
	 * @param s - Insert query string
	 * @return true if insert successful else false
	 */
	public boolean insert(String s) {
		// execute insert
		try {
			PreparedStatement statement = connection.prepareStatement(s);
			statement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("An error occured when updating the Database");
		}
		return true;
	}
	
	/**
	 * Performs Query stored in s
	 * @param s - Query string
	 * @return true if successful else false
	 */
	public ResultSet query(String s) {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement(s);
			resultSet = statement.executeQuery();
		}
		catch (SQLException e) {
			System.out.println("An error occured when querying the Database");
		}
		return resultSet;
	}
	
	public void close() {
		try {
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Error closing connection");
		}
	}
}
