package edu.unsw.comp9321.assign2;

import java.sql.*;

/*
 * Accepts bid request, processes and adds to the database if valid
 */
public class Bid {
	ConnectionManager cm;
	Connection c;
	
	public Bid() {
		cm = new ConnectionManager();
		c = cm.getConnection();
	}
	
	/*
	 * 
	 */
	public void submitBid() {
		
	}
}
