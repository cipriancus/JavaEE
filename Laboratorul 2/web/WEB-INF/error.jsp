<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 2017-10-15
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cat error</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String error = "0";//set default error

    for (Cookie cookie : cookies)
        if (cookie.getName().equals("error")) {
            error = cookie.getValue();
        }
%>

<%
    switch (error) {
        case "0":
            out.print("<p>Server error, please try again</p>");
            break;
        case "1":
            out.print("<p>Poor input error, please fill all the fields</p>");
            break;
        case "2":
            out.print("<p>Captcha error, please try again</p>");
            break;
        case "3":
            out.print("<p>There is no category with that name, please select one from the list</p>");
            break;
        case "4":
            out.print("<p>Duplicate entry error, please insert a different one</p>");
            break;
        default:
            out.print("<p>Aww snap, error, please try again</p>");
            break;
    }
%>
</body>
</html>
