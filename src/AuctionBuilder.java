package edu.unsw.comp9321.assign2;

import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuctionBuilder {
	ConnectionManager cm;
	Connection c;
	PreparedStatement insertItem; // insert item
	PreparedStatement auction; // insert item
	PreparedStatement item; // query item to get ID
	String itemString;
	String auctionString;
	String itemQueryString;
	
	public AuctionBuilder() {
		cm = new ConnectionManager();
		c = cm.getConnection();
		itemString = "INSERT INTO item " +
						"(title, category, picture, description, " +
						"postagedetails, reserveprice, bidincrement)" +
						"VALUES (?, ?, 'photo.jpg', ?, ?, ?, ?)"; 
		auctionString = "INSERT INTO auction " +
						"(item, username, starttime)" +
						"VALUES (?, ?, ?)";
		itemQueryString = "SELECT * FROM item WHERE title=? AND reserveprice=?";
		
		// prepare statement
		try {
			insertItem = c.prepareStatement(itemString);
			auction = c.prepareStatement(auctionString);
			item = c.prepareStatement(itemQueryString);
		}
		catch (SQLException s) {
			System.out.println("failed to prepare statement");
			s.printStackTrace();
		}
	}
	
	/*
	 * Adds auction to database and sends confirmation email
	 * calls addToItem
	 * calls addToAuction
	 */
	public void createAuction(HttpServletRequest request, HttpServletResponse response) {
		/*This version does not yet allow for pictures to be added*/
		boolean itemResult;
		//boolean auctionResult;
		
		itemResult = addToItem(request);
		if (itemResult) {
			addToAuction(request, response);
		}
		else {
			// item failed
		}
		
	}
	
	/*
	 * Initiates auction
	 * sets start time
	 * how to track auction not yet implemented - will need to initialize tracking
	 */
	public void confirm() { // rename to startAuction if needed
		
	}
	
	/*
	 * Adds item details to the Item table
	 */
	private boolean addToItem(HttpServletRequest request) {
		boolean result = true;
		//register.setString(1, request.getParameter("username"));
		try {
			insertItem.setString(1, request.getParameter("title"));
			insertItem.setString(2, request.getParameter("category"));
			insertItem.setString(4, request.getParameter("description"));
			insertItem.setString(5, request.getParameter("postage"));
			insertItem.setString(6, request.getParameter("bidincrement"));
			//insertItem.setString(7, request.getParameter("length"));
		}
		catch (Exception e) {
			System.out.println("Error preparing item");
			e.printStackTrace();
		}
		
		try {
			int rowsUpdated = insertItem.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("item successfully added");
				result = true;
			}
			else {
				System.out.println("addToItem: Failed to add item");
				result = false;
			}
		}
		catch (Exception e) {
			System.out.println("Error adding the item to the database");
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Adds auction details to Auction table
	 */
	private void addToAuction(HttpServletRequest request, HttpServletResponse response) {
		int itemid;
		String user = "user1"; // for user1 is adding the auction
		//boolean result = true;
		ResultSet results;
		
		// get itemid
		try {
			item.setString(1, request.getParameter("title"));
			item.setString(2, request.getParameter("reserve"));
			results = item.executeQuery();
			if (results.next()) {
				itemid = results.getInt("itemid");
				auction.setInt(1, itemid);
				auction.setString(1, user);
				
				int rowsUpdated = auction.executeUpdate();
				if (rowsUpdated > 0) {
					// update successful
					System.out.println("Auction successfully added");
					RequestDispatcher rd = request.getRequestDispatcher("Success.jsp");
					rd.forward(request, response);
					return;
				}
			}
			// dispatch to error page
			System.out.println("Auction failed");
			RequestDispatcher rd = request.getRequestDispatcher("AuctionFailed.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			System.out.println("Error occured cross checking item");
		}
		
	}
}
