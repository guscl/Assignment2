package edu.unsw.comp9321.assign2;

import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class authenticator {
	ConnectionManager cm;
	Connection c;
	
	public authenticator() {
		cm = new ConnectionManager();
		c = cm.getConnection();
	}
	
	public void login(HttpServletRequest request, 
						HttpServletResponse response) {
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username: " + user + " password: " + password);
		
		if (validator.checkText(user) && validator.checkPassword(password)) {
			try {
				PreparedStatement login = c.prepareStatement("select * " +
															 "from member " +
															 "where username=? and password=?");
				login.setString(1, user);
				login.setString(2, password);
				
				ResultSet result = login.executeQuery();
				if (result.next()) {
					System.out.println("Role is: " + result.getString("role"));
					if (result.getString("role").equals("administrator")) {
						SessionBean session = new SessionBean(user, "admin");request.setAttribute("SessionTracker", session);
						RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
						rd.forward(request, response);
					}
					else {
						SessionBean session = new SessionBean(user, "admin"); request.setAttribute("SessionTracker", session);
						RequestDispatcher rd = request.getRequestDispatcher("BidList.jsp");
						rd.forward(request, response);
					}
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}
			catch (Exception e) {
				System.out.println("An error occured");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Validation failed");
			try {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			catch (Exception e) {
				System.out.println("Validation Error occured, redirecting");
			}
			
		}
		
	}
	
}
