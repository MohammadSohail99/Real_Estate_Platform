<%@ page import="java.util.List" %>
<%@ page import="com.example.Real_Estate_Platform.Entity.Appointment" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Appointments</title>

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
    <h1>All Appointments</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Mediator Name</th>
                <th>Buyer Name</th>
                <th>Property Title</th>
                <th>Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
            for (Appointment appointment : appointments) {
                String confirmButtonClass = "confirm-button";
                String rejectButtonClass = "reject-button";
                if (appointment.getStatus().equals("Confirmed") || appointment.getStatus().equals("Rejected")) {
                    confirmButtonClass += " disabled-button";
                    rejectButtonClass += " disabled-button";
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
                    <c:if test="${appointment.getStatus() eq 'Pending'}">
                        <form action="/confirmAppointment" method="post">
                            <input type="hidden" name="mid" value="${mid}">
                            <input type="hidden" name="appointmentId" value="<%= appointment.getId() %>">
                            <input type="submit" class="<%= confirmButtonClass %>" value="Confirm">
                        </form>
                        <form action="/rejectAppointment" method="post">
                            <input type="hidden" name="mid" value="${mid}">
                            <input type="hidden" name="appointmentId" value="<%= appointment.getId() %>">
                            <input type="submit" class="<%= rejectButtonClass %>" value="Reject">
                        </form>
                    </c:if>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <form action="/">
        <input class="action-button" type="submit" value="Logout">
    </form>
</body>
</html>
