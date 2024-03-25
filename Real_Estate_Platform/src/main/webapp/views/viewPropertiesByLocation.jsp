<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Property List Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        form {
            margin-top: 20px;
            text-align: center;
        }

        input[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Property List Page</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Address</th>
            <th>Price</th>
            <th>Area</th>
            <th>Is Sold</th>
            <th>Status</th>
            <th>Mediator</th>
        </tr>

        <c:if test="${!empty propertyList}">
            <c:forEach items="${propertyList}" var="property">
                <tr>
                    <td><c:out value="${property.id}"/></td>
                    <td><c:out value="${property.title}"/></td>
                    <td><c:out value="${property.address}"/></td>
                    <td><c:out value="${property.price}"/></td>
                    <td><c:out value="${property.area}"/></td>
                    <td><c:out value="${property.sold ? 'Yes' : 'No'}"/></td>
                    <td><c:out value="${property.status}"/></td>
                    <td><c:out value="${property.seller.getMediator().getMname()}"/></td>
                    <td>
                        <c:if test="${!property.sold}">
                            <form action="scheduleAppointment">
                            <input type="hidden" name="pid" value="${property.id}">
                            <input type="hidden" name="title" value="${property.title}">
                            <input type="hidden" name="buyer_name" value="${buyer_name}">
                            <input type="hidden" name="mediator_name" value="${property.seller.getMediator().getMname()}">
                            <input type="submit" value="Book Appointment">
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <form action="/all">
        <input type="hidden" name="buyer_name" value="${buyer_name}">
        <input type="submit" value="Back">
    </form>
</body>
</html>
