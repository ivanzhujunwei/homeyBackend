package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ReturnHobbyAddress;

public class ReturnHobbyAddServlet extends HttpServlet {
	static Statement st = null;
	static ResultSet rs = null;
	private static final long serialVersionUID = 1L;

	public ReturnHobbyAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paraValue = request.getParameter("hobbyKind");
		// // try master;
		int kind = Integer.parseInt(paraValue);
		ReturnHobbyAddress wri = new ReturnHobbyAddress();
		response.setContentType("application/json");
		response.getWriter().write(wri.returnHobbyAdd(kind));
	}
}