package com.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class AllEvent {
	static Statement st = null;
	static ResultSet rs = null;

	public String connectionToMySql(String eventType, String eventSuburb, String eventTime) {

		StringBuilder sb = new StringBuilder();
		try {
			rs = ConnectDB.getStatement().executeQuery("SELECT * FROM event where type = \'" + eventType
					+ "\' and suburb = \'" + eventSuburb + "\' and eve_date = \'" + eventTime + "\';");
			sb.append("[");
			while (rs.next()) {
				sb.append("{\"id\":\"" + rs.getInt(1) + "\"," + "\"address\":\"" + rs.getString(2) + "\","
						+ "\"suburb\":\"" + rs.getString(3) + "\"," + "\"state\":\"" + rs.getString(4) + "\","
						+ "\"date\":\"" + rs.getDate(5) + "\"," + "\"type\":\"" + rs.getString(6) + "\","
						+ "\"desc\":\"" + rs.getString(7) + "\"},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	// public static void main(String[] arg) {
	//
	// connectionToMySql("Reading Writing", "ormond", "2017-08-09 14:00:00");
	// }

}
