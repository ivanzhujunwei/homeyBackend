package com.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class ReturnHobbyDetail {
	public String connectionToMySql(String input) {
		StringBuilder sb = new StringBuilder();
		try {
			ConnectDB.closeConnection();
			ConnectDB.rs = ConnectDB.getStatement()
					.executeQuery("SELECT description,image,title FROM dict where id = " + input + ";");
			while (ConnectDB.rs.next()) {
				sb.append("{\"description\":\"" + ConnectDB.rs.getString(1) + "\"," + "\"image\":\""
						+ ConnectDB.rs.getString(2) + "\"," + "\"title\":\"" + ConnectDB.rs.getString(3) + "\"}");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}

}
