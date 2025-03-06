<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>change password</title>
</head>
<body>
<h2>change password</h2>

<form action="changePasswordForCard" method="POST">

    <select name="cardNumbers"
            style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        <c:forEach var="cardNumber" items="${cardNumbers}">
            <option value=${cardNumber}> ${cardNumber}</option>

        </c:forEach>
    </select>

    <label for="oldPassword">ole password</label>
    <input type="password" id="oldPassword" name="oldPassword" required><br><br>

    <label for="newPassword">new password</label>
    <input type="password" id="newPassword" name="newPassword" required><br><br>

    <button type="submit">submit</button>
</form>
</body>
</html>