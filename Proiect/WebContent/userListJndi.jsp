<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.java.jndi.operation.UserListServlet"%>
<%
	//String name = request.getAttribute("name");
	Object name = request.getSession().getAttribute("name");
	String nCountry = String.valueOf(name);
	out.println("Attribute Value : " + nCountry);
%>
<c:set var="nm" value="<%=nCountry%>" />
<c:if test="${(nm == 'US') }">

	<sql:query var="list" dataSource="jdbc/UsersFr">
	select student_name, student_email, student_password, student_gender,student_address from student_record;
</sql:query>
</c:if>

<c:set var="nm" value="<%=nCountry%>" />
<c:if test="${(nm == 'Romania') }">

	<sql:query var="list" dataSource="jdbc/UsersRo">
	select student_name, student_email, student_password, student_gender,student_address from student_record;
</sql:query>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Students List</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of users</h2>
			</caption>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Password</th>
				<th>Gender</th>
				<th>Address</th>
			</tr>

			<c:forEach var="user" items="${list.rows}">
				<tr>
					<td><c:out value="${user.student_name}" /></td>
					<td><c:out value="${user.student_email}" /></td>
					<td><c:out value="${user.student_password}" /></td>
					<td><c:out value="${user.student_gender}" /></td>
					<td><c:out value="${user.student_address}" /></td>
				</tr>
			</c:forEach>
			<br />

		</table>
	</div>
</body>
</html>