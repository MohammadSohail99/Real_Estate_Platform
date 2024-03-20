<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Sellers</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        table.inner {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table.inner th, table.inner td {
            padding: 5px;
            border-bottom: 1px solid #ddd;
        }

        table.inner th {
            background-color: #f2f2f2;
        }

        table.inner tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<h1>All Sellers</h1>
<table border="1">
    <thead>
    <tr>
        <th>Seller ID</th>
        <th>Seller Name</th>
        <th>Seller Email</th>
        <th>Seller Mobile</th>
        <th>Role</th>
        <th>Properties</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="seller" items="${sellers}">
        <tr>
            <td>${seller.sid}</td>
            <td>${seller.sname}</td>
            <td>${seller.email}</td>
            <td>${seller.mobile}</td>
            <td>${seller.role}</td>
            <td>
                        <form action="/viewPropertiesBySeller">
                            <input type="hidden" name="sellerId" value="${seller.sid}">
                            <input type="submit" value="View Properties">
                        </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/">
    <input type="submit" value="Home Page">
</form>
</body>
</html>
