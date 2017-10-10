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
		String host = "jdbc:mysql://au-cdbr-azure-southeast-a.cloudapp.net:3306/acsm_9a1d59757257b73?autoReconnect=true&useSSL=false";
		String username = "bd31f3f5bd89f5";
		String password = "ce023044";
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
			}
			if (connect != null) {
				connect.close();
			}
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
