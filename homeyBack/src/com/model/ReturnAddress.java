package com.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.common.Logfile;
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
		case "Legal / Financial Advice":
			return "legalAdvice.png";
		case "employ":
			return "employ.png";
		default:
			return "null";
		}
	}

	public static String returnAddFanacial(String subOrPost) {
		// TODO Auto-generated method stub
		// 写一个 servlet 叫issueservlet 然后 把url用if框起来，不同的if返回不同的id 然后调用同一个方法

		if (subOrPost == null || subOrPost.length() == 0) {
			return returnAdd("", "13");
		} else {
			try {
				Integer.valueOf(subOrPost);
				String suburb = ReturnWeatherAndAct.getSuburb(subOrPost);
				return returnAdd(suburb, "13");
			} catch (Exception e) {
				if (e.toString().contains("string")) {
					return returnAdd(subOrPost, "13");
				}
			}
		}
		return "no place";
	}

	public static String returnAddAddiction(String subOrPost) {
		if (subOrPost == null || subOrPost.length() == 0) {
			return returnAdd("", "12");
		} else {
			try {
				Integer.valueOf(subOrPost);
				String suburb = ReturnWeatherAndAct.getSuburb(subOrPost);
				return returnAdd(suburb, "12");
			} catch (Exception e) {
				if (e.toString().contains("string")) {
					return returnAdd(subOrPost, "12");
				}
			}
		}
		return "no place";
	}

	public static String returnAddFamVio(String postSub) {
		if (postSub == null || postSub.length() == 0) {
			return returnAdd("", "11");
		} else {
			try {
				Integer.valueOf(postSub);
				String suburb = ReturnWeatherAndAct.getSuburb(postSub);
				return returnAdd(suburb, "11");
			} catch (Exception e) {
				if (e.toString().contains("string")) {
					return returnAdd(postSub, "11");
				}
			}
		}
		return "no place";
	}

	public static String returnAdd(String suburb, String id) {
		StringBuilder sb = new StringBuilder();
		try {
			ConnectDB.closeConnection();
			if (suburb.length() == 0) {
				ConnectDB.rs = ConnectDB.getStatement().executeQuery(
						"select name_of_trainer, address, suburb,comments, phone_number from hobby where kind = '" + id
								+ "' order by comments;");
			} else {
				ConnectDB.rs = ConnectDB.getStatement().executeQuery(
						"select name_of_trainer, address, suburb,comments, phone_number from hobby where suburb ='"
								+ suburb + "' and kind = '" + id + "' order by comments;");
			}
			sb.append("{\"allAddress\":\"");
			while (ConnectDB.rs.next()) {
				String abc = ConnectDB.rs.toString();
				sb.append(ConnectDB.rs.getString(1));
				sb.append("=");
				sb.append(ConnectDB.rs.getString(2));
				sb.append(",");
				sb.append(ConnectDB.rs.getString(3));
				sb.append("=");
				sb.append(getIcon(ConnectDB.rs.getString(4).split("=")[0]));
				sb.append("=");
				sb.append(ConnectDB.rs.getString(5).replace(" ", ""));
				if (ConnectDB.rs.getString(4).split("=").length == 1) {
					sb.append("=");
					sb.append(ConnectDB.rs.getString(4));
				} else if (ConnectDB.rs.getString(4).split("=").length == 9) {
					sb.append("=");
					sb.append(ConnectDB.rs.getString(4).split("=")[returnDay()]);
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

	public static int returnDay() {
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
