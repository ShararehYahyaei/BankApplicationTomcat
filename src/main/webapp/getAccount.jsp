<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!-- نمایش خطا در صورتی که موجود باشد -->
        <c:if test="${not empty error}">
            <p style="color: red; text-align: center;">${error}</p>
        </c:if>
        <c:if test="${not empty accountByCustomerNumber}">
            <ul>
                <c:forEach var="account" items="${accountByCustomerNumber}">
                    <li>
                        Account Number: ${account.accountNumber} - Account Type: ${account.accountType}
                    </li>
                </c:forEach>
            </ul>
        </c:if>

    </div>
</form>

</body>
</html>
