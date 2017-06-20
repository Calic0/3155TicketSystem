package com.wrox;

import java.sql.*;
import java.util.*;

public class DBManager {
	private Connection myConn;
	private Statement myStat;
	
	public DBManager(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "ITAdmin", "ticketDB");
			
			myStat = myConn.createStatement();
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	public Map<Integer, Ticket> pullAllTickets() {
		//Initializing Return Map
		Map<Integer, Ticket> returnThis = new LinkedHashMap<>();
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
			
			
			returnThis.put(myRs.getInt("RefID"), temp);
			}
		//End of Try
		}
		catch(Exception exc){
			exc.printStackTrace();
			}
		return returnThis;
		}
	
	
	
	public static void main(String[] args){
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
	}
}
	
