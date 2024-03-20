<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Review</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
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
        margin-bottom: 20px;
    }

    form {
        margin-top: 20px;
    }

    label {
        font-weight: bold;
    }

    textarea,
    input[type="number"],
    input[type="submit"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    </style>

</head>
<body>
    <h1>Add Your Review</h1>
    <form action="submitReview" method="post">
        <input type="hidden" name="propertyId" value="${propertyId}">
        <input type="hidden" name="buyerId" value="${buyerId}">
        <label for="reviewText">Review Text:</label><br>
        <textarea id="reviewText" name="reviewText" rows="4" cols="50"></textarea><br>
        <label for="rating">Rating:</label><br>
        <input type="number" id="rating" name="rating" min="1" max="5"><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
