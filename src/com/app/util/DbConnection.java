package com.app.util;

import java.sql.*;

public abstract class DbConnection {

    private final String URL = "jdbc:mysql://localhost:3306/dbgymbro";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    // String driver = "com.mysql.jdbc.Driver"; // old.. new asa baba
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public Connection con;
    public Statement state;
    public ResultSet result;
    public PreparedStatement prep;

    public void connect() throws SQLException {
	try {
		Class.forName(DRIVER); // call driver
		//open connection
		con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	} catch (ClassNotFoundException | SQLException e) {
		System.out.println("Connection Failed! " + e.getMessage());
	}

    }
    
    //MJ's setup
//    String serverName = "localhost\\SQLEXPRESS";
//    String dbName = "dbgymbro";
//    String username = "mj";
//    String password = "Password";

//    public void connect(){
//        
//        String urlConnection = "jdbc:sqlserver://" + serverName + ":1433;databaseName=" + dbName + ";encrypt=false;trustServerCertificate=true";
//
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            con = DriverManager.getConnection(urlConnection, username, password);
//            
//            if (con != null && con.isValid(2)) { // Timeout of 2 seconds
//                System.out.println("Database connected successfully!");
//            } else {
//                System.out.println("Failed to connect to the database.");
//            }
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("JDBC Driver not found!");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("Database connection failed!");
//            e.printStackTrace();
//        }
//    }
//    
    public void closeConnection() {
	try {
		if (result != null) result.close();
		if (prep != null) prep.close();
		if (con != null) con.close();
	} catch (SQLException e) {
		System.out.println("Unable to close connection: " + e.getMessage());
	}
    }
}
