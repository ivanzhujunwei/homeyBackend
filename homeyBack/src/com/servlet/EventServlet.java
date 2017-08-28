package com.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AllEvent;

public class EventServlet extends HttpServlet {
	static Statement st = null;
	static ResultSet rs = null;
	private static final long serialVersionUID = 1L;

	public EventServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();

		if ("/eventSearhServlet".equalsIgnoreCase(servletPath)) {

			// String paraTime = request.getParameter("eventTime");
			// String paraType = request.getParameter("eventType");
			// String paraSuburb = request.getParameter("eventSuburb");
			AllEvent allEvent = new AllEvent();
			response.setContentType("application/json");
			response.getWriter().write(allEvent.connectionToMySql("Reading Writing", "ormond", "2017-08-09 14:00:00"));
		}

	}
}