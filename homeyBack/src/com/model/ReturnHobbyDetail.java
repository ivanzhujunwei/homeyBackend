package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class ReturnHobbyDetail {
	static Statement st = null;
	static ResultSet rs = null;

	public String connectionToMySql(String input) {
		StringBuilder sb = new StringBuilder();
		try {
			rs = ConnectDB.getStatement()
					.executeQuery("SELECT description,image,title FROM dict where id = " + input + ";");
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
