<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Property Already Sold</title>
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
    <div class="container">
        <h1>Property Already Sold</h1>
        <p>The property you are trying to buy has already been sold.</p>
        <input type="hidden" name="bid" value="${bid}">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="address" value="${address}">
        <input type="hidden" name="minPrice" value="${minPrice}">
        <input type="hidden" name="maxPrice" value="${maxPrice}">
        <a href="search?bid=${bid}&id=${id}&address=${address}&minPrice=${minPrice}&maxPrice=${maxPrice}" class="button">Back</a>
    </div>
</body>
</html>
