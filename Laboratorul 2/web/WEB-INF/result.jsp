<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@ page import="com.laborator.repository.CategoryRepository" %>
<%@ page import="com.laborator.model.Record" %>
<%@ page import="com.laborator.repository.RecordRepository" %>

<html>
<head>
    <title>Cats Collection</title>
</head>
<body>

<%
    Cookie[] cookies = request.getCookies();
    List<Record> outputList = (List<Record>) request.getSession().getAttribute("allMyRecords");

    for (Cookie cookie : cookies)
        if (cookie.getName().equals("getAll")) {
            String value = cookie.getValue();
            if (value.equals("1")) {
                RecordRepository repository = RecordRepository.getInstance();
                outputList = repository.getAllRecords();
            }
        }
%>

<ol>
    <%
        if (outputList != null)
            for (Record iterator : outputList) {
                out.print("<li> Name of cat is " + iterator.getName() + " , it's age is " + iterator.getAge() + " and the breed is " + iterator.getCategory().getBreed() + "</li>");
            }
    %>
</ol>

</body>
</html>
