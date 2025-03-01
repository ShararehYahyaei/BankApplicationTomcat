<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Details</title>
</head>
<body>

<form action="${action}" method="get">
    <div style="max-width: 500px; background: #f4f4f4; border-radius: 8px; padding: 15px;
                border: 1px solid #ddd; box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
                font-family: monospace; white-space: pre-wrap; word-wrap: break-word;">
        <h3 style="font-size: 20px; color: #444; margin-bottom: 10px; text-align: center;">Account Details</h3>
        <p style="font-size: 16px; color: #333; margin: 0;">${accountByCustomerNumber}</p>
    </div>
</form>

</body>
</html>
