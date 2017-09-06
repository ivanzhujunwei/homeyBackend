package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class ReturnAllHobby {
	static Statement st = null;
	static ResultSet rs = null;

	public static String connectionToMySql() {
		StringBuilder sb = new StringBuilder();
		try {
			rs = ConnectDB.getStatement().executeQuery("SELECT id,name,image FROM dict;");
			sb.append("[");
			int i = 0;
			while (rs.next() && i < 6) {
				sb.append("{\"id\":\"" + rs.getInt(1) + "\"," + "\"name\":\"" + rs.getString(2) + "\"," + "\"image\":\""
						+ rs.getString(3) + "\"},");
				i++;
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		// System.out.println(sb.toString());
		return sb.toString();
	}

	// public static void main(String[] args) {
	// connectionToMySql();
	// }
}
