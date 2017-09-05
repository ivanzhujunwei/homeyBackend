package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import com.db.ConnectDB;

public class ReturnSportAddress {
	static Statement st = null;
	static ResultSet rsGetId = null;
	static ResultSet rsGetAdd = null;
	static ResultSet rsGetSuburb = null;

	// return address based on suburb and sports
	public static String returnAddOnSuburb(String suburb, String sport) {
		String[] sports = sport.split(",");
		StringBuilder sb = new StringBuilder();
		for (String sp : sports) {
			try {
				int id = 0;
				rsGetId = ConnectDB.getStatement().executeQuery("select id from dict where name ='" + sp + "';");
				while (rsGetId.next())
					id = rsGetId.getInt(1);
				rsGetAdd = ConnectDB.getStatement()
						.executeQuery("select name_of_trainer, address, suburb from hobby where suburb ='" + suburb
								+ "' and kind = '" + id + "';");
				while (rsGetAdd.next()) {
					sb.append(rsGetAdd.getString(1));
					sb.append("=");
					sb.append(rsGetAdd.getString(2));
					sb.append(",");
					sb.append(rsGetAdd.getString(3));
					sb.append("=");
					sb.append(sp);
					sb.append("^");
				}
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		// System.out.println(sb.toString());
		return sb.toString();
	}

	// return address based on longitude , latitude and sports
	// public static String returnAddOnPost(String postcode, String sport) {
	// String[] sports = sport.split(",");
	// StringBuilder sb = new StringBuilder();
	// for (String sp : sports) {
	// try {
	// int id = 0;
	// rsGetId = ConnectDB.getStatement().executeQuery("select id from dict where
	// name ='" + sp + "';");
	// while (rsGetId.next())
	// id = rsGetId.getInt(1);
	// rsGetAdd = ConnectDB.getStatement()
	// .executeQuery("select name_of_trainer, address, suburb from hobby where
	// postcode ='" + postcode
	// + "' and kind = '" + id + "';");
	// while (rsGetAdd.next()) {
	// sb.append(rsGetAdd.getString(1));
	// sb.append("=");
	// sb.append(rsGetAdd.getString(2));
	// sb.append(",");
	// sb.append(rsGetAdd.getString(3));
	// sb.append("=");
	// sb.append(sp);
	// sb.append("^");
	// }
	// } catch (SQLException e) {
	// System.out.println(e.toString());
	// }
	// }
	// if (sb.length() > 0)
	// sb.deleteCharAt(sb.length() - 1);
	// return sb.toString();
	// }
	//
	// // return postcode based on longitude and latitude
	// public static String returnPostCode(String latLon) {
	// DecimalFormat df = new DecimalFormat("#.#");
	// String lat = df.format(Double.valueOf(latLon.split(",")[0]));
	// String lon = df.format(Double.valueOf(latLon.split(",")[1]));
	//
	// StringBuilder sb = new StringBuilder();
	// try {
	// rsGetSuburb = ConnectDB.getStatement()
	// .executeQuery("select distinct(postcode) from postcodes_geo where latitude
	// like'" + lat
	// + "%' and longitude like '" + lon + "%';");
	// while (rsGetSuburb.next()) {
	// sb.append(rsGetSuburb.getString(1));
	// }
	// } catch (SQLException e) {
	// System.out.println(e.toString());
	// }
	// return sb.toString();
	// }

	// public static void main(String[] arg) {
	// // getWeatherData("34,134");
	// // returnAddOnPost("3000", "fitness,swimming,badminton,reading/wriging");
	// returnAddOnSuburb("Melbourne", "park");
	// // returnPostCode("-37.810,144.970");
	// }
}
