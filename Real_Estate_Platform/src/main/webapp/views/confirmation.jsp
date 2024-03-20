<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Appointment Confirmation</title>
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

    p {
        margin-bottom: 10px;
    }

    form {
        text-align: center;
    }

    input[type="hidden"],
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
    <h1>Appointment Scheduled Successfully</h1>
    <p>Appointment Details:</p>
    <p>ID: ${appointment.id}</p>
    <p>Mediator ID: ${appointment.mediatorId}</p>
    <p>Buyer ID: ${appointment.buyerId}</p>
    <p>Property ID: ${appointment.propertyId}</p>
    <p>Date and Time: ${appointment.dateTime}</p>
    <p>Status: ${appointment.status}</p>

    <form action="buy">
        <input type="hidden" name="bid" value="${appointment.buyerId}">
        <input type="hidden" name="id" value="${appointment.propertyId}">
        <input type="hidden" name="address" value="${address}">
        <input type="hidden" name="minPrice" value="${minPrice}">
        <input type="hidden" name="maxPrice" value="${maxPrice}">
        <input type="hidden" name="minArea" value="${minArea}">
        <input type="hidden" name="maxArea" value="${maxArea}">
        <input type="submit" value="Buy Property">
    </form>
</div>
</body>
</html>
