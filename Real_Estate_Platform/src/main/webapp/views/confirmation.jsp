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
    <p>Mediator Name: ${appointment.mediator_name}</p>
    <p>Buyer Name: ${appointment.buyer_name}</p>
    <p>Property Title: ${appointment.title}</p>
    <p>Date: ${appointment.date}</p>
    <p>Status: ${appointment.status}</p>

    <form action="/viewBookings">
        <input type="hidden" name="pid" value="${pid}">
        <input type="hidden" name="buyer_name" value="${appointment.buyer_name}">
        <input type="hidden" name="mediator_name" value="${appointment.mediator_name}">
        <input type="hidden" name="title" value="${appointment.title}">
        <input type="submit" value="view All Bookings">
    </form>
</div>
</body>
</html>
