package com.wrox;

import java.sql.*;
import java.util.*;

public class DBManager {
	private Connection myConn;
	private Statement myStat;
	private int nextRef;
	
	public DBManager(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "ITAdmin", "ticketDB");
			nextRef = 0;
			myStat = myConn.createStatement();
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	public Map<Integer, Ticket> pullAllTickets() {
		//Initializing Return Map
		Map<Integer, Ticket> returnThis = new LinkedHashMap<>();
		int max = 0;
		try{
		
		//Statement Prep
		String query = "Select * from tickettable";
		
		//SQL Access
		
			ResultSet myRs = myStat.executeQuery(query);
					
		//Creating Ticket Objects and putting into Database
		while (myRs.next()){
			Ticket temp = new Ticket();
			temp.setCustomerName("John Doe");
			temp.setSubject(myRs.getString("Subject1"));
			temp.setBody(myRs.getString("Body"));
			
			//Creating Attachment
			Attachment tempattach = new Attachment();
			tempattach.setName("placeholder");
			tempattach.setContents(myRs.getBytes("Attachment"));
			temp.addAttachment(tempattach);
			int tempInt = myRs.getInt("RefID");
			
			if(tempInt > nextRef){
				nextRef = tempInt;
			}
			returnThis.put(tempInt, temp);
			}
		//End of Try
		}
		catch(Exception exc){
			exc.printStackTrace();
			}
		return returnThis;
		}
	
	public static int delete(int refID) {
        Connection connection = myConn;
        PreparedStatement ps = null;

        String query = "DELETE FROM tickettable "
                + "WHERE RefID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, refID);
            
            return ps.executeUpdate();  
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
	
	public void insert(Ticket ticket) {
        Connection connection = myConn;
        PreparedStatement ps = null;
        Random rando = new Random();
        String query
                = "INSERT INTO tickettable (RefID, UserID, Subject1, Body, Date_Opened) " //Date closed was removed temporarily
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(4, ticket.getBody());
            ps.setInt(1, ticket.getRefID());
            java.sql.Date sqlDate = null;
            if(ticket.getDateCreated()!=null){
            	sqlDate = new java.sql.Date(ticket.getDateCreated().getTime());
            }
            java.sql.Date sqlDateClosed = null;
            if(ticket.getDateClosed()!=null){
            	 sqlDateClosed = new java.sql.Date(ticket.getDateClosed().getTime());
            }
            ps.setDate(5, sqlDate); //Instance vs Date issue
            ps.setString(3, ticket.getSubject());	
            ps.setInt(2, 2);
            //ps.setDate(6, sqlDateClosed);
            //Again figure out attachment issue
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }
	
	
	public void createUser(int UserID, String Username, String pass, String First, String Last){
		PreparedStatement ps = null;
		
		String query
			= "INSERT IGNORE INTO Users(UserID, Password, First, Last, Date_Created, Last_Session) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try{
			ps = myConn.prepareStatement(query);
			ps.setInt(1, UserID);
			ps.setString(2, Username);
			ps.setString(3, pass);
			ps.setString(4, First);
			ps.setString(5, Last);
			java.sql.Date sqlDate = new Java.sql.Date(utilDate.getTime());
			ps.setDate(6, sqlDate);
			ps.setDate(7, sqlDate);
			ps.executeUpdate();
		} catch (SQLException e){
			System.out.println(e);			
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	
	
	/*public static void main(String[] args){
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "ITAdmin", "ticketDB");
			//Create a Statement
			Statement myStat = myConn.createStatement();
			//Execute a Query
			ResultSet myRs = myStat.executeQuery("Select * From tickettable");
			//Process a Result Set
			while(myRs.next()){
				System.out.println(myRs.getString("UserID")+ ", " + myRs.getString("RefID"));
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "ITAdmin", "ticketDB");
			//Create a Statement
			Statement myStat = myConn.createStatement();
			//Execute a Query
			myStat.executeUpdate(" INSERT INTO tickettable VALUES (2,	4, 'Example2',	'Another example2',	null,	null, 0, null)");
			//Execute a Query
			ResultSet myRs = myStat.executeQuery("Select * From tickettable");
			//Process a Result Set
			while(myRs.next()){
				System.out.println(myRs.getString("UserID")+ ", " + myRs.getString("RefID"));
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}*/
}
	
