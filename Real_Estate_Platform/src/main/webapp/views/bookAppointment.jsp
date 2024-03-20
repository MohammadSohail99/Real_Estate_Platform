<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Schedule Appointment</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

    .container {
        width: 50%;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    form {
        text-align: center;
    }

    input[type="text"],
    input[type="datetime-local"],
    input[type="submit"] {
        margin: 5px;
        padding: 8px 16px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }

    </style>

</head>
<body>
<div class="container">
    <h1>Schedule Appointment</h1>
    <form action="/scheduleAppointmentBuyer" method="post">
        <input type="hidden" name="id" value="${propertyId}">
        <input type="hidden" name="bid" value="${buyerId}">
        <input type="hidden" name="address" value="${property.address}">
        <input type="hidden" name="minPrice" value="${minPrice}">
        <input type="hidden" name="maxPrice" value="${maxPrice}">
        <input type="hidden" name="minArea" value="${minArea}">
        <input type="hidden" name="maxArea" value="${maxArea}">
        Mediator ID: <input type="text" name="mediatorId"><br>
        Buyer ID: <input type="text" name="bid" value="${buyerId}"><br>
        Property ID: <input type="text" name="id" value="${propertyId}"><br>
        Date and Time: <input type="datetime-local" name="dateTime"><br>
        <input type="submit" value="Schedule Appointment">
    </form>
</div>
</body>
</html>
