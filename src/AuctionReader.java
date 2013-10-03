package edu.unsw.comp9321.assign2;

import java.sql.*;

/*
 * Reads Auctions from the database and returns ResultSets
 * Provides several methods to allow flexibility in accessing auctions
 */
public class AuctionReader {
	ConnectionManager cm;
	Connection c;
	String auctionQuery = "";
	PreparedStatement query;
	
	public AuctionReader() {
		cm = new ConnectionManager();
		c = cm.getConnection();
		try {
			query = c.prepareStatement(auctionQuery);
		}
		catch (SQLException s) {
			System.out.println("Error preparing statment");
			s.printStackTrace();
		}
	}
	
	/*
	 * Returns set of results for auctions in database
	 * of results are not specified
	 */
	public ResultSet getAuctions() {
		ResultSet set = null;
		try {
			set = query.executeQuery();
		}
		catch (SQLException s) {
			System.out.println("Error executing query");
			s.printStackTrace();
		}
		return set;
	}
	
	/*
	 * Searches for actions matching searchString
	 * String format TBA
	 */
	public ResultSet searchAuctions(String searchString) {
		return null;
	}
}
