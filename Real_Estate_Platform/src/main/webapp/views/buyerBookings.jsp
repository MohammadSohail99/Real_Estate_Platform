<%@ page import="java.util.List" %>
<%@ page import="com.example.Real_Estate_Platform.Entity.Appointment" %>

<!DOCTYPE html>
<html>
<head>
    <title>Buyer Bookings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        h1 {
            color: #007bff;
        }
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
    </style>
</head>
<body>
    <h1>Buyer Bookings</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Mediator ID</th>
                <th>Buyer ID</th>
                <th>Property ID</th>
                <th>Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
                for (Appointment appointment : appointments) {
            %>
                <tr>
                    <td><%= appointment.getId() %></td>
                    <td><%= appointment.getMediatorId() %></td>
                    <td><%= appointment.getBuyerId() %></td>
                    <td><%= appointment.getPropertyId() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getStatus() %></td>
                    <td>
                            <form action="/buy" method="post">
                                <input type="hidden" name="bid" value="${bid}">
                                <input type="hidden" name="id" value="${id}">
                                <input type="hidden" name="address" value="${address}">
                                <input type="hidden" name="minPrice" value="${minPrice}">
                                <input type="hidden" name="maxPrice" value="${maxPrice}">
                                <input type="hidden" name="minArea" value="${minArea}">
                                <input type="hidden" name="maxArea" value="${maxArea}">
                                <input type="submit" value="Buy">
                            </form>

                        <c:if test="${appointment.getStatus().equals('Rejected')}">
                            <button disabled>Buy</button>
                        </c:if>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
