<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Delete</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 50%;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
    }

    form {
        text-align: center;
    }

    input[type="submit"], a {
        display: inline-block;
        padding: 10px 20px;
        margin-top: 20px;
        text-decoration: none;
        color: #fff;
        background-color: #007bff;
        border: 1px solid #007bff;
        border-radius: 5px;
    }

    a {
        margin-left: 10px;
        background-color: #6c757d;
        border-color: #6c757d;
    }

    input[type="submit"]:hover, a:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }


    </style>
</head>
<body>
    <h1>Confirm Delete</h1>
    <p>Are you sure you want to delete the property with ID ${property.id}?</p>
    <form action="deleteProperty" method="post">
        <input type="hidden" name="id" value="${property.id}">
        <input type="submit" value="Yes">
        <a href="javascript:history.go(-1)">No, Go Back</a>
    </form>
</body>
</html>
