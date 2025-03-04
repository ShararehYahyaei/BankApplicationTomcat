<%--
  Created by IntelliJ IDEA.
  User: sharareh
  Date: 02/03/2025
  Time: 1:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create card</title>
</head>
<body>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        width: 350px;
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
        text-align: center;
    }
    input, button {
        width: 100%;
        padding: 10px;
        margin: 8px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    button {
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        font-size: 16px;
    }
    button:hover {
        background-color: #0056b3;
    }
    .error {
        color: red;
        text-align: center;
    }
    .message {
        color: green;
        text-align: center;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Create Card</h2>
    <form action="/createCard" method="post">
        <input type="text" name="cardNumber" placeholder="Card Number" required>
        <input type="text" name="cvv2" placeholder="CVV2" required>
        <input type="date" name="expiryDate" placeholder="Expiry Date" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="text" name="customerNumber" placeholder="Customer Number" required>
        <input type="text" name="accountNumber" placeholder="account Number" required>
        <button type="submit">Submit</button>
    </form>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>
</div>
</body>
</html>
