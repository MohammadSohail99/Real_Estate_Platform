<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Property</title>
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
    <h1>Edit Property</h1>
    <form action="updateProperty" method="post">
        <input type="hidden" name="id" value="${property.id}">
        <label>Title:</label>
        <input type="text" name="title" value="${property.title}"><br>
        <label>Address:</label>
        <input type="text" name="address" value="${property.address}"><br>
        <label>Price:</label>
        <input type="text" name="price" value="${property.price}"><br>
        <label>Area:</label>
        <input type="text" name="area" value="${property.area}"><br>
        <label>Is Sold:</label>
        <input type="checkbox" name="sold" ${property.sold ? 'checked' : ''}><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
