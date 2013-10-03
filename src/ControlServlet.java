package edu.unsw.comp9321.assign2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
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
		
		if (request.getParameter("action").equals("login")) { 
			System.out.println("Perform login");
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("username: " + user + " password: " + password);
			
			
			authenticator a = new authenticator();
			a.login(user, password, request, response);
			
		}
		else if (request.getParameter("action").equals("register")) {
			Registration r = new Registration();
			r.register(request, response);
		}
		else if (request.getParameter("action").equals("addAuction")) {
			AuctionBuilder ab = new AuctionBuilder();
			ab.createAuction(request, response);
		}
		else if (request.getParameter("action").equals("bid")) {
			
		}
		else if (request.getParameter("action").equals("adminLogin")) {
			
		}
	}
	
}
