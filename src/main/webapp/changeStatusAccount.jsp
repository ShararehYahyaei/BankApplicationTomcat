<%--
  Created by IntelliJ IDEA.
  User: sharareh
  Date: 05/03/2025
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/changeStatusForAccount" method="post">
  <label for="accountNumber">Account Number:</label>
  <input type="text" id="accountNumber" name="accountNumber" required>
  <label for="isActive">Account Status:</label>
  <select name="isActive" id="isActive">
    <option value="true">Active</option>
    <option value="false">Inactive</option>
  </select>

  <button type="submit">Change Status</button>
</form>

</body>
</html>
