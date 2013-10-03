package edu.unsw.comp9321.assign2;

import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration {
	ConnectionManager cm;
	Connection c;
	PreparedStatement register;
	String updateString;
	String uName;
	String nName;
	String fName;
	String lName;
	String password;
	String email;
	Integer birthYear;
	String address;
	String creditCard;
	
	public Registration() {
		cm = new ConnectionManager();
		c = cm.getConnection();
		updateString = "INSERT INTO member " +
							"(username, nickname, firstname," +
							" lastname, password, email, birthyear, " + 
							"address, creditcard, role, locked)" +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'member', false)";
		// prepare statement
		try {
			register = c.prepareStatement(updateString);
		}
		catch (SQLException s) {
			System.out.println("failed to prepare statement");
			s.printStackTrace();
		}
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * substitute values passed via the request object
		 */
		try {
			register.setString(1, request.getParameter("username"));
			register.setString(2, request.getParameter("nickname"));
			register.setString(3, request.getParameter("firstname"));
			register.setString(4, request.getParameter("lastname"));
			register.setString(5, request.getParameter("password"));
			register.setString(6, request.getParameter("email"));
			register.setInt(7, Integer.parseInt(request.getParameter("birthyear")));
			register.setString(8, request.getParameter("address"));
			register.setString(9, request.getParameter("creditcard"));
			
		}
		catch (Exception e) {
			System.out.println("An error has occured generating update");
			e.printStackTrace();
		}
		
		/*
		 * Execute query and redirect according to result
		 */
		try {
			int rowsUpdated = register.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("User successfully registered");
				
				RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
				rd.forward(request, response);
			}
			else {
				System.out.println("Registration failed");
				redirectFailedAttempt(request, response);
			}
		}
		catch (Exception e) {
			System.out.println("An exception occured while executing query");
			e.printStackTrace();
			redirectFailedAttempt(request, response);
		}
		
	}
	
	private void redirectFailedAttempt(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher("FailedRegistration.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			System.out.println("If you see this error something is very wrong");
		}
	}
	
	// if time permits create method to check data before insert
}
