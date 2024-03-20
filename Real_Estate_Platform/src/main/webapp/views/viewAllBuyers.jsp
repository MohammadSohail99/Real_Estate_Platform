<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buyer Information</title>
</head>
<body>
    <h1>Buyer Information</h1>
    <c:if test="${buyer != null}">
        <p><strong>Name:</strong> ${buyer.bname}</p>
        <p><strong>Email:</strong> ${buyer.email}</p>
        <p><strong>Mobile:</strong> ${buyer.mobile}</p>
    </c:if>

    <h1>Property Information</h1>
    <c:if test="${property != null}">
            <p><strong>Title:</strong> ${property.title}</p>
            <p><strong>Location:</strong> ${property.address}</p>
            <p><strong>price:</strong> ${property.price}</p>
            <p><strong>area:</strong> ${property.area}</p>
            <p><strong>Status:</strong> ${property.status}</p>
        </c:if>

    <h2>Total Price: ${total}</h2>

    <form action="/viewAllSeller">
        <input type="submit" value="Back">
    </form>
</body>
</html>
