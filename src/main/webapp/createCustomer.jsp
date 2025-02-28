<%--
  Created by IntelliJ IDEA.
  User: sharareh
  Date: 28/02/2025
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Customer</title>
</head>
<body>
<form action="/createCustomer" method="post">
    <input type="text" name="fullName" placeholder="fullName"/>
    <input type="text" name="lastName" placeholder="lastName"/>
    <input type="text" name="email" placeholder="email"/>
    <input type="text" name="phone" placeholder="phone"/>
    <input type="text" name="accountType" placeholder="accountType"/>
    <input type="text" name="balance" placeholder="balance"/>
    <input type="text" name="accountNumber" placeholder="accountNumber"/>
    <input type="text" name="code" placeholder="code"/>
    <input type="text" name="userName" placeholder="userName"/>
    <input type="password" name="password" placeholder="password"/>
    <input type="text" name="customerNumber" placeholder="customerNumber"/>
    <button type="submit">submit</button>
</form>
</body>
</html>
