<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body style="font-family: Arial, sans-serif; background-color: #f7f7f7; margin: 0; padding: 0;">
<div style="width: 80%; margin: 50px auto; padding: 30px; background-color: #fff; border-radius: 10px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);">
    <h2 style="text-align: center; color: #333; font-size: 24px; margin-bottom: 20px;">Customer List</h2>

    <c:forEach var="customer" items="${customers}">
        <div style="display: flex; flex-direction: column; border-bottom: 1px solid #eee; padding: 15px 0;">
            <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Full Name:</strong> ${customer.fullName}</p>
            <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Last Name:</strong> ${customer.lastName}</p>
            <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Email:</strong> ${customer.email}</p>
            <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Phone:</strong> ${customer.phone}</p>
            <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>User Name:</strong> ${customer.userName}</p>
            <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Customer Number:</strong> ${customer.customerNumber}</p>
        </div>
    </c:forEach>
</div>
</body>
</html>
