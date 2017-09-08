package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.db.ConnectDB;

public class ReturnAddress {

	public static String returnAddOnSuburb(String suburb, String sport) {
		String[] sports = sport.split(",");
		StringBuilder sb = new StringBuilder();
		for (String sp : sports) {
			try {
				int id = 0;
				ConnectDB.closeConnection();
				ConnectDB.rs = ConnectDB.getStatement().executeQuery("select id from dict where name ='" + sp + "';");
				while (ConnectDB.rs.next())
					id = ConnectDB.rs.getInt(1);
				ConnectDB.closeConnection();
				ConnectDB.rs = ConnectDB.getStatement()
						.executeQuery("select name_of_trainer, address, suburb from hobby where suburb ='" + suburb
								+ "' and kind = '" + id + "';");
				while (ConnectDB.rs.next()) {
					sb.append(ConnectDB.rs.getString(1));
					sb.append("=");
					sb.append(ConnectDB.rs.getString(2));
					sb.append(",");
					sb.append(ConnectDB.rs.getString(3));
					sb.append("=");
					sb.append(sp);
					sb.append("=");
					sb.append(getIcon(sp));
					sb.append("^");
				}
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		ConnectDB.closeConnection();
		return sb.toString();
	}

	public static String getIcon(String sport) {
		switch (sport) {
		case "Park":
			return "bicycle";
		case "Reading":
			return "reading.png";
		case "Fintness":
			return "fitness.png";
		case "Swimming":
			return "swimming.png";
		case "Badminton":
			return "badminton.png";
		case "Health Services / Pharmacy":
			return "pharmacy.png";
		case "Hospitals / Emergency":
			return "hospital.png";
		case "Counselling and Psychiatric Services":
			return "counselling.png";
		default:
			return "null";
		}

	}

	public static String returnAddFamVio(String postSub) {
		try {
			Integer.valueOf(postSub);
			String suburb = ReturnWeatherAndAct.getSuburb(postSub);
			return returnFamilyAdd(suburb);
		} catch (Exception e) {
			if (e.toString().contains("string")) {
				return returnFamilyAdd(postSub);
			}
		}
		return "no place";
	}

	public static String returnFamilyAdd(String suburb) {
		StringBuilder sb = new StringBuilder();
		try {
			ConnectDB.closeConnection();
			ConnectDB.rs = ConnectDB.getStatement().executeQuery(
					"select name_of_trainer, address, suburb,comments, phone_number from hobby where suburb ='" + suburb
							+ "' and kind = '11';");
			sb.append("{\"allAddress\":\"");
			while (ConnectDB.rs.next()) {
				sb.append(ConnectDB.rs.getString(1));
				sb.append("=");
				sb.append(ConnectDB.rs.getString(2));
				sb.append(",");
				sb.append(ConnectDB.rs.getString(3));
				sb.append("=");
				sb.append(getIcon(ConnectDB.rs.getString(4).split("=")[0]));
				sb.append("=");
				sb.append(ConnectDB.rs.getString(5));
				sb.append("=");
				sb.append(ConnectDB.rs.getString(4).split("=")[returnDay()]);
				if (ConnectDB.rs.getString(4).split("=").length == 9) {
					sb.append("=");
					sb.append(ConnectDB.rs.getString(4).split("=")[8]);
				} else {
					sb.append("=");
					sb.append("not available");
				}
				sb.append("^");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\"}");
		return sb.toString();
	}

	// 0 Counselling and Psychiatric Services =
	// 1 9.00am - 5.00pm =
	// 2 9.00am - 5.00pm =
	// 3 9.00am - 5.00pm =
	// 4 9.00am - 5.00pm =
	// 5 9.00am - 5.00pm =
	// 6 Closed =
	// 7 Closed =
	// 8 24, 109 (stop 18 Grant St) =
	// 9 bus

	public static int returnDay() {
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		return calendar.get(Calendar.DAY_OF_WEEK);
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
	// getWeatherData("34,134");
	// returnAddOnPost("3000", "fitness,swimming,badminton,reading/wriging");
	// returnAddOnSuburb("Melbourne", "park");
	// returnPostCode("-37.810,144.970");
	// returnDay();
	// }
}
