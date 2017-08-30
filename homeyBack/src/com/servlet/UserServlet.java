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
import com.model.supportUserServlet;

public class UserServlet extends HttpServlet {
	static Statement st = null;
	static ResultSet rs = null;
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();

		if ("/loginServlet".equalsIgnoreCase(servletPath)) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			supportUserServlet lv = new supportUserServlet();
			response.setContentType("application/json");
			response.getWriter().write(lv.whetherUserValid(userName, password));
		} else if ("/registerServlet".equalsIgnoreCase(servletPath)) {

		}
	}
}
