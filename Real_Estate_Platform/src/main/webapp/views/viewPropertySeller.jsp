<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Properties for Seller</title>
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

       .sold {
           background-color: #fff; /* White for sold properties */
       }

       .not-sold {
           background-color: #ccffcc; /* Light green for unsold properties */
       }

       .action-button {
           padding: 5px 10px;
           background-color: #007bff; /* Blue button color */
           color: white;
           border: none;
           cursor: pointer;
       }

       .action-button:hover {
           background-color: #0056b3; /* Darker blue on hover */
       }

    </style>
</head>
<body>
<h1>Properties for Seller</h1>
<c:if test="${not empty properties}">
    <table border="1">
        <thead>
        <tr>
            <th>Property ID</th>
            <th>Title</th>
            <th>Address</th>
            <th>Price</th>
            <th>Area</th>
            <th>Status</th>
            <th>Buyer Info</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="property" items="${properties}">
            <tr class="${property.sold ? 'sold' : 'not-sold'}">
                <td>${property.id}</td>
                <td>${property.title}</td>
                <td>${property.address}</td>
                <td>${property.price}</td>
                <td>${property.area}</td>
                <td>${property.status}</td>
                <td>
                    <c:choose>
                        <c:when test="${property.sold}">
                            <form action="/viewBuyer">
                                <input type="hidden" name="propertyId" value="${property.id}">
                                <input class="action-button" type="submit" value="View Buyer">
                            </form>
                        </c:when>
                        <c:otherwise>
                            Property not sold
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty properties}">
    <p>No properties found for this seller.</p>
</c:if>
<form action="/viewAllSeller">
    <input class="action-button" type="submit" value="Back to All Sellers">
</form>
</body>
</html>
