package com.java.jndi.operation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

@WebServlet("/listUsers")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Locale usa = new Locale("", "US");
		usa.toString().substring(1, 3);
		System.out.println(usa.getDisplayCountry());
		Locale romania = new Locale("", "RO");
		System.out.println(romania.getDisplayCountry());
		PrintWriter writer = response.getWriter();

		HttpSession session = request.getSession(true);

		if ((usa.toString().substring(1, 3)).equals(request.getLocale().getCountry())) {

			session.setAttribute("name", usa.toString().substring(1, 3));

			RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/userListJndi.jsp");
			errorDispatcher.forward(request, response);
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/UsersFr");
				Connection conn = ds.getConnection();

				Statement statement = conn.createStatement();

				String sql = "select student_name, student_email, student_password, student_gender,"
						+ "student_address from student_record";
				ResultSet rs = statement.executeQuery(sql);

				int count = 1;
				while (rs.next()) {
					writer.println(String.format("User #%d: %-15s %s", count++, rs.getString("student_name"),
							rs.getString("student_email"), rs.getString("student_password"),
							rs.getString("student_gender"), rs.getString("student_address")));

				}
			} catch (NamingException ex) {
				System.err.println(ex);
			} catch (SQLException ex) {
				System.err.println(ex);
			}
		} else if (romania.equals(request.getLocale().getCountry())) {
			Object name = request.getAttribute("name");
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/UsersRo");
				Connection conn = ds.getConnection();

				Statement statement = conn.createStatement();

				String sql = "select student_name, student_email, student_password, student_gender,"
						+ "student_address from student_record";
				ResultSet rs = statement.executeQuery(sql);

				int count = 1;
				while (rs.next()) {
					writer.println(String.format("User #%d: %-15s %s", count++, rs.getString("student_name"),
							rs.getString("student_email"), rs.getString("student_password"),
							rs.getString("student_gender"), rs.getString("student_address")));

				}
			} catch (NamingException ex) {
				System.err.println(ex);
			} catch (SQLException ex) {
				System.err.println(ex);
			}
		}
	}

}
