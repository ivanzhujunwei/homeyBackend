package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.Logfile;
import com.model.AllEvent;
import com.model.ReturnWeatherAndAct;

public class WeatherServlet extends HttpServlet {
	static Statement st = null;
	static ResultSet rs = null;
	private static final long serialVersionUID = 1L;

	public WeatherServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// start time
		long startTime = System.nanoTime();
		//Logfile.log("Start at "+ startTime);
		String servletPath = request.getServletPath();
		if ("/lonLatServlet".equalsIgnoreCase(servletPath)) {
			// initialize
			String lat = request.getParameter("lat");
			String lon = request.getParameter("lon");

			response.setContentType("application/json");
			response.getWriter().write(ReturnWeatherAndAct.returnOnLatLon(lat + "," + lon));
			// end time 1
			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;
			Logfile.log("Time for assembling data after receiving lon lai: "+ duration);
			// response.getWriter().write(ReturnWeatherAndAct.returnOnLatLon("-37.8770506,147.04464130000002"));
		} else if ("/postOrSubServlet".equalsIgnoreCase(servletPath)) {
			String postOrSub = request.getParameter("postOrSub");

			response.setContentType("application/json");
			response.getWriter().write(ReturnWeatherAndAct.returnOnPostOrSub(postOrSub));
			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;
			Logfile.log("Time for assembling data after receiving postcode or suburb: "+ duration);
			// response.getWriter().write(ReturnWeatherAndAct.returnOnPostOrSub("3000"));
		}
	}
}