package com.java.jndi.operation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/listUsers")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
			Connection conn = ds.getConnection();
			
			Statement statement = conn.createStatement();
			String sql = "select student_name, student_email, student_password, student_gender,"
					+ "student_address from student_record";
			ResultSet rs = statement.executeQuery(sql);
			
			int count = 1;
			while (rs.next()) {
				writer.println(String.format("User #%d: %-15s %s", count++, 
						rs.getString("student_name"), rs.getString("student_email"), rs.getString("student_password"), 
						rs.getString("student_gender"), rs.getString("student_address")));
				
			}
		} catch (NamingException ex) {
			System.err.println(ex);
		} catch (SQLException ex) {
			System.err.println(ex);
		}
	}

}
