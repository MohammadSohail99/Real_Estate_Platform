<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Buyer</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    form {
        width: 50%;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    td {
        padding: 8px;
        border-bottom: 1px solid #ddd;
    }

    label {
        font-weight: bold;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"] {
        width: calc(100% - 16px);
        padding: 8px;
        margin: 8px 0;
        border: 1px solid #ddd;
        border-radius: 3px;
    }

    input[type="submit"] {
        width: 100%;
        padding: 8px;
        margin-top: 16px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    .error {
        color: red;
        font-size: 12px;
    }

    </style>
</head>
<body>
    <h1>Register Buyer</h1>
    <form:form action="/registerBuyers" modelAttribute="buyer">
        <table>
            <tr>
                <td><label for="bname">Buyer Name:</label></td>
                <td><form:input path="bname" id="bname" /></td>
                <td><form:errors path="bname" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><form:input path="username" id="username" /></td>
                <td><form:errors path="username" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><form:password path="password" id="password" /></td>
                <td><form:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="address">Address:</label></td>
                <td><form:input path="address" id="address" /></td>
                <td><form:errors path="address" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="mobile">Mobile:</label></td>
                <td><form:input path="mobile" id="mobile" type="number" /></td>
                <td><form:errors path="mobile" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><form:input path="email" id="email" /></td>
                <td><form:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
