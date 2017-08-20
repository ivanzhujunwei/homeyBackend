package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class ReturnHobbyDetail {
	static Statement st = null;
	static ResultSet rs = null;

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String connectionToMySql(String input) {
		connection();

		String host = "jdbc:mysql://localhost:3306/Homey?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "tjh199208";
		//
		// String host =
		// "jdbc:mysql://au-cdbr-azure-southeast-a.cloudapp.net:3306/homeymsql?autoReconnect=true&useSSL=false";
		// String username = "b84c8d66cf6a94";
		// String password = "d3bbe525";

		StringBuilder sb = new StringBuilder();
		try {
//			Connection connect = DriverManager.getConnection(host, username, password);
//			st = connect.createStatement();
//			// rs = st.executeQuery("SELECT description,image,title FROM dict where id = "+  input +";");
//			rs = st.executeQuery("SELECT description,image,title FROM dict where id = 1;");
			rs = ConnectDB.getStatement().executeQuery("SELECT description,image,title FROM dict where id = "+  input +";");
			while (rs.next()) {
				sb.append("{\"description\":\"" + rs.getString(1) + "\"," + "\"image\":\"" + rs.getString(2) + "\","
						+ "\"title\":\"" + rs.getString(3) + "\"}");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}

}
