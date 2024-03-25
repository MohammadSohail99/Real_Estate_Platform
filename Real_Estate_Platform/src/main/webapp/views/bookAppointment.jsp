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
    <p>${errorMessage}</p>
    <form action="/scheduleAppointment">
        <input type="hidden" name="pid" value="${pid}">
        <input type="hidden" name="title" value="${title}">
         <input type="hidden" name="mediator_name" value="${mediator_name}">
                <input type="hidden" name="buyer_name" value="${buyer_name}">
        <input class="action-button" type="submit" value="Back">
    </form>
</div>
<div class="container">
    <h1>Schedule Appointment</h1>
    <form action="/scheduleAppointmentBuyer" method="post">
        <input type="hidden" name="pid" value="${pid}">
        Mediator Name: <input type="text" name="mediator_name" value="${mediator_name}"><br>
        Buyer Name: <input type="text" name="buyer_name" value="${buyer_name}"><br>
        Property Title: <input type="text" name="title" value="${title}"><br>
        Date: <input type="date" name="date"><br>
        <input type="submit" value="Schedule Appointment">
    </form>
</div>
</body>
</html>
