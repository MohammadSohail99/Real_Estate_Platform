<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Real Estate Property Form</title>
    <style>
    /* Style for form container */
    body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        </style>
</head>
<body>
    <h1>Enter Property Details</h1>
    <form:form action="saveProperty" method="post" modelAttribute="propertyModel">
        <input type="hidden" name="sid" value="${sid}">

        <label for="title">Title:</label>
        <form:input path="title" id="title"/><br/>
        <form:errors path="title" cssClass="error"/><br/>

        <label for="address">Address:</label>
        <form:input path="address" id="address"/><br/>
        <form:errors path="address" cssClass="error"/><br/>

        <label for="price">Price:</label>
        <form:input path="price" id="price"/><br/>
        <form:errors path="price" cssClass="error"/><br/>

        <label for="area">Area:</label>
        <form:input path="area" id="area"/><br/>
        <form:errors path="area" cssClass="error"/><br/>

        <label for="isSold">Is Sold:</label>
        <form:checkbox path="isSold" id="isSold"/><br/><br/>

        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
