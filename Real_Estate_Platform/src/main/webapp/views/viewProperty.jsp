<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Property Details</title>
</head>
<body>
    <h1>Property Details</h1>
    <h2>Property Information</h2>
    <ul>
        <li><strong>ID:</strong> ${property.id}</li>
        <li><strong>Title:</strong> ${property.title}</li>
        <li><strong>Address:</strong> ${property.address}</li>
        <li><strong>Price:</strong> ${property.price}</li>
        <li><strong>Area:</strong> ${property.area}</li>
        <li><strong>Is Sold:</strong> ${property.sold ? 'Yes' : 'No'}</li>
        <li><strong>Status:</strong> ${property.status}</li>
    </ul>
    <h2>Seller Information</h2>
    <ul>
        <li><strong>ID:</strong> ${property.seller.sid}</li>
        <li><strong>Name:</strong> ${property.seller.sname}</li>
        <li><strong>Email:</strong> ${property.seller.email}</li>
        <li><strong>Mobile:</strong> ${property.seller.mobile}</li>
    </ul>
</body>
</html>
