package com.model;

import java.sql.SQLException;

import com.common.Logfile;
import com.db.ConnectDB;

public class ReturnAllHobby {

	public static String connectionToMySql() {
		StringBuilder sb = new StringBuilder();
		long startTime = System.nanoTime();
		try {
			ConnectDB.closeConnection();
			ConnectDB.rs = ConnectDB.getStatement().executeQuery("SELECT id,name,image FROM dict;");
			sb.append("[");
			int i = 0;
			while (ConnectDB.rs.next() && i < 6) {
				sb.append("{\"id\":\"" + ConnectDB.rs.getInt(1) + "\"," + "\"name\":\"" + ConnectDB.rs.getString(2)
						+ "\"," + "\"image\":\"" + ConnectDB.rs.getString(3) + "\"},");
				i++;
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			// end time 1
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / 1000000;
			Logfile.log("Time for assembling data after getting all hobbies: " + duration);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}

	// public static void main(String[] args) {
	// connectionToMySql();
	// }
}
