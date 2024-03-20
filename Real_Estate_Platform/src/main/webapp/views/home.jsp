<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.Real_Estate_Platform.Entity.Property" %>
<%@ page import="com.example.Real_Estate_Platform.Entity.Buyer" %>

<html>
<head>
    <title>Home Page</title>
    <style>
   body {
       font-family: Arial, sans-serif;
       margin: 20px;
       padding: 0;
       line-height: 1.5; /* Add space between lines */
   }

   h1, h2, p {
       margin: 0 0 10px 0; /* Add space below each heading and paragraph */
       padding: 0;
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
       font-size: 16px; /* Maintain font size */
   }

   th {
       background-color: #f2f2f2;
   }

   tr:nth-child(even) {
       background-color: #f2f2f2;
   }

   tr:hover {
       background-color: #ddd;
   }


    </style>

</head>
<body>
    <h1>Welcome to the Home Page</h1>
    <h2>Seller Information</h2>
    <p>Seller ID: ${seller.sid}</p>
    <p>Seller Name: ${seller.sname}</p>

    <h2>Available Properties</h2>
    <table>
        <thead>
            <tr>
                <th>Property ID</th>
                <th>Property Title</th>
                <th>Price</th>
                <th>Area</th>
                <th>Address</th>
                <th>Is Sold</th>
                <th>Status</td>
                <th>Buyers</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="property" items="${properties}">
                <tr>
                    <td>${property.id}</td>
                    <td>${property.title}</td>
                    <td>${property.price}</td>
                    <td>${property.area}</td>
                    <td>${property.address}</td>
                    <td>${property.sold ? 'Yes' : 'No'}</td>
                    <td>${property.status}</td>
                    <td>
                       <c:if test="${property.buyer ne null}">
                           <table>
                               <tr>
                                   <th>Name</th>
                                   <th>Email</th>
                                   <th>Mobile</th>
                                   <th>Address</th>
                               </tr>
                               <tr>
                                   <td>${property.buyer.bname}</td>
                                   <td>${property.buyer.email}</td>
                                   <td>${property.buyer.mobile}</td>
                                   <td>${property.buyer.address}</td>
                               </tr>
                           </table>
                       </c:if>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <form action="addProperty" method="get">
        <input type="hidden" name="sid" value="${seller.sid}">
        <button type="submit">Add Property</button>
    </form>
    <form action="/">
             <input type="submit" value="LogOut">
    </form>
</body>
</html>
