package com.cs441.autorep.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://54.172.105.21/autorep2";

	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "autorepcs441";
	   
	   public static java.sql.Connection getConnection() throws SQLException, ClassNotFoundException{
		   java.sql.Connection conn = null;
		   
		  
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      return conn;
		   
	   }
}
