package com;

import java.sql.*;

public class CusAccount {

	private static String url = "jdbc:mysql://localhost:3306/eg_db";
	private static String userName = "root";
	private static String password = "Udesh@1975";
	
	
	public Connection connect()
	{
	Connection con = null;
	
	try
	{
	  Class.forName("com.mysql.jdbc.Driver");
	  con= DriverManager.getConnection(url,userName,password);
	  //For testing
	  System.out.print("Successfully connected");
	}
	catch(Exception e)
	{
		System.out.println("Database connection is not success!!!");
	}
	
	return con;
	}

	//user account insert method
			public String insertAccount(String AccountNumber, String name, String NIC, String Phone,String Email)
			{
			   String output = "";
			  try
			   {
			      Connection con = connect();
			      if (con == null)
			   {
			    return "Error while connecting to the database";
			   }
			   // create a prepared statement
			   String query = "insert into eg_db.customer (idcustomer,AccountNumber,Name,NIC,Phone,Email) values (?,?,?,?,?,?)";
			   
			   
			   PreparedStatement preparedStmt = con.prepareStatement(query);
			   // binding values
			   preparedStmt.setInt(1, 0);
			   preparedStmt.setInt(2,Integer.parseInt(AccountNumber));
			   preparedStmt.setString(3, name);
			   preparedStmt.setString(4,NIC);
			   preparedStmt.setString(5, Phone);
			   preparedStmt.setString(6, Email);
			
			
			  //execute the statement
			  preparedStmt.execute();
			  con.close();
			  String newCustomer = readAccount();
			   output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
			  }
			  catch (Exception e)
			  {
			  output = "{\"status\":\"error\", \"data\": \"Error while inserting the Account.\"}";
			  System.err.println(e.getMessage());
			  }
			  return output;
			  }
			

			//View Account function
			
			public String readAccount()
			  {
			     String output = "";
			     try
			    {
			      Connection con = connect();
			      if (con == null)
			    {
			        return "Error while connecting to the database for reading.";
			    }
			// Prepare the html table to be displayed
			   output = "<table border='1'><tr><th>AccountNumber</th>"
			            +"<th>Name</th><th>NIC</th>"
			            + "<th>Phone</th>"
			            + "<th>Email</th>" + "<th>Update</th><th>Remove</th></tr>";
			           
			   
			    String query = "select * from customer";
			    Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			   while (rs.next())
			   {
			     String idcustomer = Integer.toString(rs.getInt("idcustomer"));
			     String AccountNumber = Integer.toString(rs.getInt("AccountNumber"));
			     String Name = rs.getString("Name");
			     String NIC = rs.getString("NIC");
			     String Phone = rs.getString("Phone");
			     String Email = rs.getString("Email");
	                    
	                     output += "<tr><td><input id=\'hidCustomerIDUpdate\' name=\'hidCustomerIDUpdate\' type=\'hidden\' value=\'"
							+ idcustomer + "'>" + AccountNumber + "</td>";
					output += "<td>" + Name + "</td>";
					output += "<td>" + NIC + "</td>";
					output += "<td>" + Phone + "</td>";
					output += "<td>" + Email + "</td>";
					

					// buttons
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-idcustomer='"
							+ idcustomer + "'>" + "</td></tr>";
			   }
			    con.close();
			// Complete the html tables
			    output += "</table>";
			    
			    }
			   catch (Exception e)
			   {
			      output = "Error while reading the Account.";
			      System.err.println(e.getMessage());
			   }
			    return output;
			 }
			

			   //update
					public String updateAccount(String ID, String ACnumber, String name, String NIC, String Phone,String Email)
					
					{
					    String output = "";
					try
					{
					   Connection con = connect();
					   if (con == null)
					    {  return "Error while connecting to the database for updating."; }
					// create a prepared statement
					  String query = "UPDATE customer SET AccountNumber=?,Name=?,NIC=?,Phone=?,Email=? WHERE idcustomer=?";
					  PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					   preparedStmt.setInt(1, Integer.parseInt(ACnumber));
					   preparedStmt.setString(2, name);
					   preparedStmt.setString(3, NIC);
					   preparedStmt.setString(4, Phone);
					   preparedStmt.setString(5, Email);
					   preparedStmt.setInt(6, Integer.parseInt(ID));
					// execute the statement
					   preparedStmt.execute();
					   con.close();
					   
			                   String newCustomer = readAccount();
					   output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
					}
					catch (Exception e)
					{
					   output = "{\"status\":\"error\", \"data\": \"Error while updating the Details.\"}";
					   System.err.println(e.getMessage());
					}
					   return output;
				 }

				
			


					//delete account function
					
					public String deleteAccount(String cusID)
					  {
					   String output = "";
					 try
					  {
					    Connection con = connect();
					    if (con == null)
					   {
					       return "Error while connecting to the database for deleting.";
					   }
					// create a prepared statement
					      String query = "delete from customer where idcustomer=?";
					      PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					      preparedStmt.setInt(1, Integer.parseInt(cusID));
					// execute the statement
					      preparedStmt.execute();
					      con.close();

					      String newCustomer = readAccount();
					      output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
					 }
					 catch (Exception e)
					 {
					      output = "Error while deleting the Account.";
					      System.err.println(e.getMessage());
					 }
					 return output;
					 }
}