package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class AllEvent {

	public String connectionToMySql(String eventType, String eventSuburb, String eventTime) {

		StringBuilder sb = new StringBuilder();
		try {
			ConnectDB.closeConnection();
			ConnectDB.rs = ConnectDB.getStatement().executeQuery("SELECT * FROM event where type = \'" + eventType
					+ "\' and suburb = \'" + eventSuburb + "\' and eve_date = \'" + eventTime + "\';");
			sb.append("[");
			while (ConnectDB.rs.next()) {
				sb.append("{\"id\":\"" + ConnectDB.rs.getInt(1) + "\"," + "\"address\":\"" + ConnectDB.rs.getString(2)
						+ "\"," + "\"suburb\":\"" + ConnectDB.rs.getString(3) + "\"," + "\"state\":\""
						+ ConnectDB.rs.getString(4) + "\"," + "\"date\":\"" + ConnectDB.rs.getDate(5) + "\","
						+ "\"type\":\"" + ConnectDB.rs.getString(6) + "\"," + "\"desc\":\"" + ConnectDB.rs.getString(7)
						+ "\"},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	// public static void main(String[] args) {
	// connectionToMySql("Reading Writing", "ormond", "2017-08-09 14:00:00");
	// }

}
