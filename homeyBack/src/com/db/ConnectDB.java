package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	static Statement st = null;

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Statement getStatement() {
		connection();
		String host = "jdbc:mysql://au-cdbr-azure-southeast-a.cloudapp.net:3306/homeymsql?autoReconnect=true&useSSL=false";
		String username = "b84c8d66cf6a94";
		String password = "d3bbe525";
		Connection connect;
		try {
			connect = DriverManager.getConnection(host, username, password);
			st = connect.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
}
