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
        .confirm-button {
            padding: 5px 10px;
            background-color: #28a745; /* Green button color */
            color: white;
            border: none;
            cursor: pointer;
        }
        .confirm-button:hover {
            background-color: #218838; /* Darker green on hover */
        }
        .reject-button {
            padding: 5px 10px;
            background-color: #dc3545; /* Red button color */
            color: white;
            border: none;
            cursor: pointer;
        }
        .reject-button:hover {
            background-color: #c82333; /* Darker red on hover */
        }
        .disabled-button {
            opacity: 0.5;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <h1>Buyer Bookings</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Mediator Name</th>
                <th>Buyer Name</th>
                <th>Property Title</th>
                <th>Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
                for (Appointment appointment : appointments) {
                    String confirmButtonClass = "confirm-button";
                    String rejectButtonClass = "reject-button";
                    String actionMessage = "";
                    if (appointment.getStatus().equals("Confirmed")) {
                        actionMessage = "Buy";
                    } else {
                        confirmButtonClass += " disabled-button";
                        rejectButtonClass += " disabled-button";
                        if (appointment.getStatus().equals("Pending") || appointment.getStatus().equals("Rejected")) {
                            actionMessage = "Cannot Buy";
                        }
                    }
            %>
                <tr>
                    <td><%= appointment.getId() %></td>
                    <td><%= appointment.getMediator_name() %></td>
                    <td><%= appointment.getBuyer_name() %></td>
                    <td><%= appointment.getTitle() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getStatus() %></td>
                    <td>
                        <% if (actionMessage.equals("Buy")) { %>
                            <form action="/buy" method="post">
                                <input type="hidden" name="pid" value="${pid}">
                                <input type="hidden" name="buyer_name" value="<%= appointment.getBuyer_name() %>">
                                <input type="hidden" name="title" value="<%= appointment.getTitle() %>">
                                <input type="hidden" name="mediator_name" value="<%= appointment.getMediator_name() %>">
                                <input type="submit" class="<%= confirmButtonClass %>" value="<%= actionMessage %>">
                            </form>
                        <% } else { %>
                            <%= actionMessage %>
                        <% } %>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
