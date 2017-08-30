package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectDB;

public class supportUserServlet {
	static Statement st = null;
	static ResultSet rs = null;

	public String returnPass(String userName) {

		StringBuilder sb = new StringBuilder();
		try {
			rs = ConnectDB.getStatement().executeQuery("SELECT passWord FROM user where name = \'" + userName + "\';");
			while (rs.next()) {
				sb.append(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}

	public String whetherUserValid(String userName, String password) {
		if (password.equals(returnPass(userName))) {
			return "[{\"status\" : \"1\"}]";
		} else {
			return "[{\"status\" : \"0\"}]";
		}
	}

	public void userRegistration(String userName, String password) {

	}

}
