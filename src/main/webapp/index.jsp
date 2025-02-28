<%--
  Created by IntelliJ IDEA.
  User: sharareh
  Date: 28/02/2025
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank Application</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h2>Welcome to the Bank Application</h2>
<a href="/createCustomer" style="display: inline-block;
 padding: 10px 15px; background: #52a883; color: white;
  text-decoration: none; border-radius: 5px;">
    Go to Create Customer
</a>
<a href="/getAllCustomers" style="display: inline-block;
 padding: 10px 15px; background: #52a883; color: white;
  text-decoration: none; border-radius: 5px;">
    Show All Customers
</a>
<form action="/getCustomer" method="get">
    <input type="text" name="customerNumber" placeholder="Enter Customer Number" required>
    <button type="submit">Show Customer</button>
</form>

</body>
</html>


</body>
</html>
