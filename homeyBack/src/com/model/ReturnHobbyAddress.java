package com.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class ReturnHobbyAddress {
	static Statement st = null;

	public String returnHobbyAdd(int input) {
		StringBuilder sb = new StringBuilder();
		try {
			ConnectDB.closeConnection();
			ConnectDB.rs = ConnectDB.getStatement().executeQuery("select * from hobby where kind =" + input + ";");
			sb.append("{\"allAddress\":\"");
			while (ConnectDB.rs.next()) {
				sb.append(ConnectDB.rs.getString(3));
				sb.append(",");
				sb.append(ConnectDB.rs.getString(4));
				sb.append(",");
				sb.append(ConnectDB.rs.getString(5));
				sb.append(",");
				sb.append(ConnectDB.rs.getInt(6) + "^");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\"}");
		// return jsonTransfer(hobbyArray); 之前返回所有jason 格式的address
		return sb.toString();
	}
}