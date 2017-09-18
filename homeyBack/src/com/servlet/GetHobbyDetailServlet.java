package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ReturnHobbyDetail;

public class GetHobbyDetailServlet extends HttpServlet {
	static Statement st = null;
	static ResultSet rs = null;
	private static final long serialVersionUID = 1L;

	public GetHobbyDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paraValue = request.getParameter("hobbyKind");
		ReturnHobbyDetail rd = new ReturnHobbyDetail();
		response.setContentType("application/json");
		response.getWriter().write(rd.connectionToMySql(paraValue));
	}
}