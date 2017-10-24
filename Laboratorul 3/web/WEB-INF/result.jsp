<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="myTag" uri="/WEB-INF/customTag.tld" %>

<html>
<head>
    <title>Cats Collection</title>
</head>
<body>
<br><br>
<table>
    <tbody>
    <tr>
        <th>ID</th>
        <th>Category</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <c:forEach items="${sessionScope.allMyRecords}" var="rec">
        <tr>
            <td><c:out value="${rec.id}"></c:out></td>
            <td><c:out value="${rec.name}"></c:out></td>
            <td><c:out value="${rec.category.breed}"></c:out></td>
            <td><c:out value="${rec.age}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
    <p><myTag:record category="Wirehair" name="tomy"></myTag:record></p>
</body>
</html>
