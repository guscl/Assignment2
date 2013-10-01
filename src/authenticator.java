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
	
	public void login(String user, String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PreparedStatement login = c.prepareStatement("select * " +
														 "from member " +
														 "where username=? and password=?");
			login.setString(1, user);
			login.setString(2, password);
			ResultSet result = login.executeQuery();
			if (result.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("LoginFailed.jsp");
				rd.forward(request, response);
			}
		}
		catch (Exception e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
}
