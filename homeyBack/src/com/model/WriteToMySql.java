package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.db.ConnectDB;

public class WriteToMySql {
	static Statement st = null;
	static ResultSet rs = null;

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String connectionToMySql(int input) {
		connection();
//		String host = "jdbc:mysql://localhost:3306/Homey?autoReconnect=true&useSSL=false";
//		String username = "root";
//		String password = "tjh199208";
		
//		 String host = "jdbc:mysql://au-cdbr-azure-southeast-a.cloudapp.net:3306/homeymsql?autoReconnect=true&useSSL=false";
//		 String username = "b84c8d66cf6a94";
//		 String password = "d3bbe525";

		ArrayList<Hobby> hobbyArray = new ArrayList<>();
		try {
//			Connection connect = DriverManager.getConnection(host, username, password);
//			st = connect.createStatement();
//			rs = st.executeQuery("SELECT * FROM hobby where kind = " + input + ";");
			rs = ConnectDB.getStatement().executeQuery("select * from hobby where kind="+input+";");
			while (rs.next()) {
				Hobby hobbyData = new Hobby();
				hobbyData.setName_of_hobby(rs.getString(1));
				hobbyData.setAddress(rs.getString(2));
				hobbyData.setSuburb(rs.getString(3));
				hobbyData.setState(rs.getString(4));
				hobbyData.setPostcode(rs.getInt(5));
				hobbyData.setPhone_number(rs.getString(6));
				hobbyData.setComments(rs.getString(7));
				hobbyArray.add(hobbyData);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		return jsonTransfer(hobbyArray);
	}

	public String jsonTransfer(ArrayList<Hobby> arrayList) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arrayList.size(); i++) {
			sb.append("{\"name\":\"");
			// sb.append(arrayList.get(i).getKind());
			// sb.append("\",\"name_of_trainer\":\"");
			sb.append(arrayList.get(i).getName_of_hobby());
			sb.append("\",\"address\":\"");
			sb.append(arrayList.get(i).getAddress());
			sb.append("\",\"suburb\":\"");
			sb.append(arrayList.get(i).getSuburb());
			sb.append("\",\"state\":\"");
			sb.append(arrayList.get(i).getState());
			sb.append("\",\"postcode\":\"");
			sb.append(arrayList.get(i).postcode());
			sb.append("\",\"phone_number\":\"");
			sb.append(arrayList.get(i).getPhone_number());
			sb.append("\",\"comments\":\"");
			sb.append(arrayList.get(i).getComments() + "\"},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
}
