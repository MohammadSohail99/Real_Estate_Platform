<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Buyers</title>
</head>
<body>
    <h1>Properties and Buyers</h1>
    <h1>Buyers for Seller ${sellerId}</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Property ID</th>
                <th>Title</th>
                <th>Price</th>
                <th>Buyers</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="property" items="${properties}">
                <tr>
                    <td>${property.id}</td>
                    <td>${property.title}</td>
                    <td>${property.price}</td>
                    <td>
                        <c:forEach var="buyer" items="${property.buyers}">
                            <p>${buyer.bname} (${buyer.email})</p>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
