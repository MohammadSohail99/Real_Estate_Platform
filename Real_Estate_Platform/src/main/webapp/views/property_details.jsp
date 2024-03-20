<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Purchase Confirmation</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        p {
            margin-top: 20px;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }

        button:hover {
            background-color: #0056b3;
        }

        </style>
</head>
<body>
    <h1>Purchase Details for your purchase!</h1>
    <input type="hidden" name="bid" value="${bid}">

    <c:if test="${buyer != null}">
        <h2>Buyer Information:</h2>
        <p>Name: ${buyer.bname}</p>
        <p>Email: ${buyer.email}</p>
        <p>Mobile: ${buyer.mobile}</p>
        <p>Address: ${buyer.address}</p>
    </c:if>

    <c:if test="${property != null}">
        <h2>Property Information:</h2>
        <p>Location: ${property.address}</p>
        <p>Price: ${property.price}</p>
        <p>Title: ${property.title}</p>
        <p>Area: ${property.area}</p>
         <p>Status: ${property.status}</p>
    </c:if>

    <h2>Total Price:</h2>
    <p>${totalPrice}</p>

    <form action="addReview">
        <input type="hidden" name="propertyId" value="${property.id}">
        <input type="hidden" name="buyerId" value="${buyer.bid}">
        <input type="submit" value="Add Review">
    </form>
</body>
</html>
