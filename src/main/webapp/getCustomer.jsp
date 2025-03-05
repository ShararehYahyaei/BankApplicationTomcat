<%--
  Created by IntelliJ IDEA.
  User: sharareh
  Date: 28/02/2025
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${action}" method="get">
    <div style="display: flex; flex-direction: column; border-bottom: 1px solid #eee; padding: 15px 0;">
        <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Full Name:</strong> ${byCustomerNumber.fullName}
        </p>
        <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Last Name:</strong> ${byCustomerNumber.lastName}
        </p>
        <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Email:</strong> ${byCustomerNumber.email}</p>
        <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Phone:</strong> ${byCustomerNumber.phone}</p>
        <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>User Name:</strong> ${byCustomerNumber.userName}
        </p>
        <p style="font-size: 18px; color: #333; margin: 5px 0;"><strong>Customer
            Number:</strong> ${byCustomerNumber.customerNumber}</p>
    </div>
</form>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
<div style="margin-top: 20px;">
    <!-- دکمه ایجاد کارت -->
    <a href="/createCard?customerNumber=${byCustomerNumber.customerNumber}"
       style="display: inline-block; padding: 10px 15px; background: #007bff;
       color: white; text-decoration: none; border-radius: 5px; margin-right: 10px;">
        ایجاد کارت
    </a>

    <a href="/changeStatusForAccount?customerNumber=${byCustomerNumber.customerNumber}"
       style="display: inline-block; padding: 10px 15px; background: #007bff;
       color: white; text-decoration: none; border-radius: 5px; margin-right: 10px;">
        تغییر وضعیت حساب
    </a>

    <!-- دکمه بازگشت به صفحه قبل -->
    <button onclick="window.history.back();"
            style="padding: 10px 15px; background: #dc3545; color: white;
            text-decoration: none; border-radius: 5px; border: none; cursor: pointer;">
        بازگشت به صفحه قبل
    </button>
</div>

</body>
</html>
