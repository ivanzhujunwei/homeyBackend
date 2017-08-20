package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class ReturnAllHobby {
	static Statement st = null;
	static ResultSet rs = null;

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String connectionToMySql() {
		connection();
		String host = "jdbc:mysql://localhost:3306/Homey?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "tjh199208";

		// String host =
		// "jdbc:mysql://au-cdbr-azure-southeast-a.cloudapp.net:3306/homeymsql?autoReconnect=true&useSSL=false";
		// String username = "b84c8d66cf6a94";
		// String password = "d3bbe525";

		StringBuilder sb = new StringBuilder();
		try {
			rs = ConnectDB.getStatement().executeQuery("SELECT id,name,image FROM dict;");
//			Connection connect = DriverManager.getConnection(host, username, password);
//			st = connect.createStatement();
//			rs = st.executeQuery("SELECT id,name,image FROM dict;");
			sb.append("[");
			while (rs.next()) {
				sb.append("{\"id\":\"" + rs.getInt(1) + "\"," + "\"name\":\"" + rs.getString(2) + "\"," + "\"image\":\""
						+ rs.getString(3) + "\"},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}

}
