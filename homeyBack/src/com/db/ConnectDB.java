package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	public static Statement st = null;
	public static Connection connect = null;
	public static ResultSet rs = null;

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Statement getStatement() {
		connection();
		String host = "jdbc:mysql://au-cdbr-azure-southeast-a.cloudapp.net:3306/homeydb?autoReconnect=true&useSSL=false";
		String username = "be47b226a641c9";
		String password = "136a5ca4";
		// String host =
		// "jdbc:mysql://localhost:3306/Homey?autoReconnect=true&useSSL=false";
		// String username = "root";
		// String password = "tjh199208";

		try {
			connect = DriverManager.getConnection(host, username, password);
			st = connect.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

	public static void closeConnection() {
		try {
			if (st != null) {
				st.close();
				st = null;
			}
			if (connect != null) {
				connect.close();
				connect = null;
			}
			// System.out.println(connect.isClosed());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	// public static void main(String[] arg) {
	// // // getWeatherData("34,134");
	// // // connectionToMySql(1);
	// // returnAddOnPost("3000");
	// // closeConnection();
	// // getStatement();
	// }
}
