<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@ page import="com.laborator.repository.CategoryRepository" %>
<%@ page import="com.laborator.model.Category" %>

<html>
<head>
    <title>Cats Collection</title>
</head>
<body>

<%
    CategoryRepository categoryRepository = CategoryRepository.getInstance();
    Cookie[] cookies = request.getCookies();
    Category defaultOption = null;

    for (Cookie cookie : cookies)
        if (cookie.getName().equals("defaultValue")) {
            String value = cookie.getValue();
            defaultOption = categoryRepository.getCategoryByName(value);
        }
%>

<form method="post" action="/functions/input">

    <select name="category">
        <%
            for (Category iterator : categoryRepository.getAllCategory()) {
                if (defaultOption != null && iterator.getBreed().equals(defaultOption.getBreed()))
                    out.print("<option selected=\"selected\" value=\"" + iterator.getBreed() + "\"> " + iterator.getBreed() + "</option>");
                else
                    out.print("<option value=\"" + iterator.getBreed() + "\"> " + iterator.getBreed() + "</option>");
            }
        %>
    </select>

    <input type="text" placeholder="name" name="name" required>
    <input type="text" placeholder="age" name="age" required>
    <button type="submit">Submit</button>
</form>

</body>
</html>
