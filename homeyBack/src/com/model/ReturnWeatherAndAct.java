package com.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.common.Logfile;

//weather api
public class ReturnWeatherAndAct {
	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?&APPID=ddc2cd1804e64a346f64e61c5a10c814&lat=<&lon=>";
	private static String BASE_URL_SUBURB = "http://api.openweathermap.org/data/2.5/weather?&APPID=ddc2cd1804e64a346f64e61c5a10c814&q=>";
	private static String BASE_URL_POSTCODE = "http://api.openweathermap.org/data/2.5/weather?&APPID=ddc2cd1804e64a346f64e61c5a10c814&zip=>,au";

	public static String getWeatherDataLatLon(String latLon, String suburb, String postcode) {

		HttpURLConnection con = null;
		InputStream is = null;
		try {
			if (latLon.length() != 0 && suburb.length() == 0 && postcode.length() == 0) {
				String flag[] = latLon.split(",");
				String lat = flag[0];
				String lon = flag[1];
				con = (HttpURLConnection) (new URL(BASE_URL.replace("<", lat).replace(">", lon)).openConnection());
			} else if (latLon.length() == 0 && suburb.length() != 0 && postcode.length() == 0)
				con = (HttpURLConnection) (new URL(BASE_URL_SUBURB.replace(">", suburb)).openConnection());
			else if (latLon.length() == 0 && suburb.length() == 0 && postcode.length() != 0)
				con = (HttpURLConnection) (new URL(BASE_URL_POSTCODE.replace(">", postcode)).openConnection());

			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			// Let's read the response
			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null)
				buffer.append(line + "\r\n");
			is.close();
			con.disconnect();
			// System.out.println(buffer.toString().split(":"));
			return buffer.toString();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
			try {
				con.disconnect();
			} catch (Throwable t) {
			}
		}
		return "error";
	}

	public static String getSuburb(String post) {
		StringBuilder sb = new StringBuilder();
		String[] transfer = getWeatherDataLatLon("", "", post).split(":");
		for (int i = 30; i < transfer.length; i++) {
			if (transfer[i].contains("name")) {
				sb.append(transfer[i + 1].split(",")[0].replaceAll("\"", ""));
				return sb.toString();
			}
		}
		return sb.toString();
	}

	// return all info based on the latitude and longtitude, support /weatherServlet
	public static String returnOnLatLon(String latLon) {
		String[] transfer = getWeatherDataLatLon(latLon, "", "").split(":");
		String suburb = "";
		for (int i = 29; i < transfer.length; i++) {
			if (transfer[i].contains("name")) {
				suburb = transfer[i + 1].split(",")[0].replaceAll("\"", "");
				i = transfer.length;
			}
		}
		return returnAllInfo(transfer, suburb);
	}

	//
	public static String returnOnPostOrSub(String postOrSub) {
		String suburb = "";
		try {
			int postcode = Integer.valueOf(postOrSub);
			String[] transfer = getWeatherDataLatLon("", "", postOrSub).split(":");
			for (int i = 30; i < transfer.length; i++) {
				if (transfer[i].contains("name")) {
					suburb = transfer[i + 1].split(",")[0].replaceAll("\"", "");
					return returnAllInfo(transfer, suburb);
				}
			}
		} catch (Exception e) {
			if (e.toString().contains("string")) {
				String[] transfer = getWeatherDataLatLon("", postOrSub, "").split(":");
				for (int i = 30; i < transfer.length; i++) {
					if (transfer[i].contains("name")) {
						suburb = transfer[i + 1].split(",")[0].replaceAll("\"", "");
						return returnAllInfo(transfer, postOrSub);
					}
				}
			} else {
				return e.toString();
			}
		}
		return "no such activity";
	}

	public static String returnAllInfo(String[] transfer, String suburb) {
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.#");
		// start time
		if (transfer[6].contains("Rain") || transfer[6].contains("Mist") || transfer[6].contains("Haze")
				|| transfer[6].contains("Fog") || transfer[6].contains("Drizzle")) {
			sb.append("[{\"class\":\"rainy\",\"suburb\":\"").append(suburb).append("\",\"weather\":")
					.append(transfer[6].split(",")[0]).append(", \"degree\":\"")
					.append(df.format((Double.parseDouble(transfer[11].split(",")[0]) - 273.15)))
					.append("\",\"allEvents\": \"Fitness^Swimming^Badminton^Reading\",\"allAddress\":\"")
					.append(ReturnAddress.returnAddOnSuburb(suburb, "Fintness,Swimming,Badminton,Reading"))
					.append("\",\"allEventsClass\":\"icon-sport-072^icon-sport-164^icon-sport-007^icon-education-137\",\"allEventsBgColor\":\"g-bg-pink^g-bg-blue^g-bg-orange^g-bg-purple\"}]");
			return sb.toString();

		} else if (transfer[6].contains("Clear") || transfer[6].contains("Cloud")) {
			String type = "";
			if (transfer[6].contains("Clear"))
				type = "sunny";
			else
				type = "cloudy";
			// return weather info, degree, activities, and the address when weather is
			// clear and cloud

			sb.append("[" + "{\"class\":\"").append(type).append("\",\"suburb\":\"").append(suburb)
					.append("\",\"weather\":").append(transfer[6].split(",")[0]).append(", \"degree\":\"")
					.append(df.format((Double.parseDouble(transfer[11].split(",")[0]) - 273.15)))
					.append("\",\"allEvents\": \"Bicycle,Running or Walking^Fitness^Swimming^Badminton^Reading\",\"allAddress\": \"")
					.append(ReturnAddress.returnAddOnSuburb(suburb, "Park,Reading,Fintness,Swimming,Badminton"))
					.append("\",\"allEventsClass\":\"icon-travel-090^icon-sport-072^icon-sport-164^icon-sport-007^icon-education-137\",\"allEventsBgColor\":\"g-bg-lightred^g-bg-pink^g-bg-blue^g-bg-orange^g-bg-purple\"}]");
			return sb.toString();
		}
		return sb.toString();
	}

	// public static void main(String[] arg) {
	// getWeatherDataLatLon("34,134", "", "");
	// // returnOnLatLon("-37.810,144.970");
	// // returnOnPostOrSub("3000");
	// }package

}
