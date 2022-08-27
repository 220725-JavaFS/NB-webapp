package com.revature.utils;

import java.sql.Connection;//java.sql is the JDBC package
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
		
		//Singleton design pattern only allows one instance of a Class to exist in memory at a time.
		
		private static Connection connection;
		
		public static Connection getConnection() throws SQLException {
			if (connection!=null && !connection.isClosed()) { //this makes it a singleton connection
				return connection;
			}else {
				
				//For many frameworks, or cases where there are multiple SQL drivers, you will need to register which
				//Driver you are using for the connection interface. The Class.forName method will allow you to do this.
				//This step is often redundant or often unnecessary for simple projects but is considered best practice.
				
				try {
					Class.forName("org.postgresql.Driver");
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				String url = "jdbc:postgresql://javafs220725.c7uqwmtdtwsg.us-east-1.rds.amazonaws.com:5432/project1";
				String username = "postgres";
				String password = "password";
				
				connection = DriverManager.getConnection(url, username, password);
				
				return connection;
			}
		}

		public static void main (String[]args) {
			try {
				getConnection();
				System.out.println("Connection Successful!");
			} catch (SQLException e) {
				System.out.println("Hmm that didn't work");
				e.printStackTrace();
		}
	}
}

