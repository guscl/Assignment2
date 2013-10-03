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
						"(item, username, auctionlength)" +
						"VALUES (?, ?, ?)";
		itemQueryString = "SELECT * FROM item WHERE title=?";
		
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
		
		itemResult = addToItem(request);
		if (itemResult) {
			addToAuction(request, response);
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
		try {
			insertItem.setString(1, request.getParameter("title"));
			insertItem.setString(2, request.getParameter("category"));
			// photo
			insertItem.setString(3, request.getParameter("description"));
			insertItem.setString(4, request.getParameter("postage"));
			insertItem.setString(5, request.getParameter("reserve"));
			insertItem.setString(6, request.getParameter("bidincrement"));
			
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
		ResultSet results;
		
		// get itemid
		itemid = getItemID(request.getParameter("title"));
		
		System.out.println("Have item id, now adding the auction");
		try {
			auction.setInt(1, itemid);
			auction.setString(2, user);
			auction.setInt(3, Integer.parseInt(request.getParameter("length")));
			
			int updateCount = auction.executeUpdate();
			if (updateCount > 0) {
				System.out.println("Auction successfully added");
				// implement email function here
				RequestDispatcher rd = request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
				return;
			}
			
			// Default redirect to indicate failure
			redirectFailedAttempt(request, response);
		}
		catch (Exception e) {
			System.out.print("Error submitting Auction");
			e.printStackTrace();
			redirectFailedAttempt(request, response);
		}
			
	}
	
	private Integer getItemID(String title) {
		int itemNum = 0;
		ResultSet rs;
		
		System.out.println("getItemId called");
		
		try {
			item.setString(1,"iphone 5"); // title
			rs = item.executeQuery();
			if (rs.next()) {
				System.out.println("have resultset");
				itemNum = rs.getInt(1);
				String titleName = rs.getString(2);
				System.out.println("itemID: " + itemNum + " title: " + titleName);
			}
			else {
				System.out.println("No set returned"); // will return 0
			}
		}
		catch (Exception e) {
			System.out.println("Failed to obtain item id");
			e.printStackTrace();
		}
		
		return itemNum;
	}
	
	private void redirectFailedAttempt(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rdFailed = request.getRequestDispatcher("AuctionFailed.jsp");
			rdFailed.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
