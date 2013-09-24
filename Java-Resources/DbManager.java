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
	 * version 0.1
	 * Skeleton code for DbManager
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
	public boolean register(String uName, String passwd) {
		String newUser = "INSERT INTO member" + 
				"(username, nickname, firstname, lastname, email, birthyear, address, creditcard, role)" +
				" VALUES ('" + uName + "', '" + passwd + "')";
		insert(newUser); // fix db to include passwd
		return true;
	}
	
	/**
	 * authenticates users already in Database
	 * @param uName - Username
	 * @param passwd - Users Password
	 * @return true if credentials found else false
	 */
	public boolean authenticate(String uName, String passwd) {
		String credentials = "SELECT userName, passwd FROM member WHERE " +
				uName + "=username";
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
		String item;
		String auction;
		
		return true;
	}
	
	/**
	 * Submits bid to the Database
	 * @param s - String containing bid details
	 * @return true if bid successfully written to database
	 */
	public boolean submitBid(String s) {
		
		return true;
	}
	
	/**
	 * Performs Insert query stored in s
	 * @param s - Insert query string
	 * @return true if insert successful else false
	 */
	public boolean insert(String s) {
		// execute insert
		return true;
	}
	
	/**
	 * Performs Query stored in s
	 * @param s - Query string
	 * @return true if successful else false
	 */
	public String query(String s) {
		// execute query
		return "";
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
