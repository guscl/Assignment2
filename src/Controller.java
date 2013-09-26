package edu.unsw.comp9321;

/**
 * Group 2 Assignment 2 - Auction Web App
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet Controller v0.1
 * Checks request for action and redirects page
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost called");
		
		// check for action requested
		if (request.getParameter("action").equals("login")) {
			System.out.println();
			ConnectionManager m = new ConnectionManager(); // consider making static
			Connection c = m.getConnection();
			Authenticator a = new Authenticator(c);
			a.login(request, response);
			
		}
		else if (request.getParameter("action").equals("addUser")) {
			System.out.println("Registering user into Database");
			// call method to do insert method
			Registration register = new Registration();
			/*RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp"); 
			rd.forward(request, response);*/
		}
		else if (request.getParameter("action").equalsIgnoreCase("addAuction")) {
			
		}
		else if (request.getParameter("action").equalsIgnoreCase("SubmitBid")) {
			
		}
		
	}

}
