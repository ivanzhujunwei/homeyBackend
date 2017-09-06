package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.db.ConnectDB;

public class WriteToMySql {
	static Statement st = null;
	static ResultSet rs = null;

	public String connectionToMySql(int input) {

		ArrayList<Hobby> hobbyArray = new ArrayList<>();
		try {
			rs = ConnectDB.getStatement().executeQuery("select * from hobby where kind =" + input + ";");
			while (rs.next()) {
				Hobby hobbyData = new Hobby();
				hobbyData.setName_of_hobby(rs.getString(2));
				hobbyData.setAddress(rs.getString(3));
				hobbyData.setSuburb(rs.getString(4));
				hobbyData.setState(rs.getString(5));
				hobbyData.setPostcode(rs.getInt(6));
				hobbyData.setPhone_number(rs.getString(7));
				hobbyData.setComments(rs.getString(8));
				hobbyArray.add(hobbyData);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		// return jsonTransfer(hobbyArray); 之前返回所有jason 格式的address
		return addressArray(hobbyArray);
	}

	public String addressArray(ArrayList<Hobby> arrayList) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"allAddress\":\"");
		for (int i = 0; i < arrayList.size(); i++) {
			// sb.append("\'");
			sb.append(arrayList.get(i).getAddress());
			sb.append(",");
			sb.append(arrayList.get(i).getSuburb());
			sb.append(",");
			sb.append(arrayList.get(i).getState());
			sb.append(",");
			sb.append(arrayList.get(i).postcode());
			sb.append(",");
			sb.append("Library");
			sb.append("^");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\"}");
		return sb.toString();
	}

	// 好想没的用了。
	public String jsonTransfer(ArrayList<Hobby> arrayList) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arrayList.size(); i++) {
			sb.append("{\"name\":\"");
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

	// public static void main(String[] arg) {
	// // getWeatherData("34,134");
	// // connectionToMySql(1);
	// returnAddOnPost("3000");
	// }
}