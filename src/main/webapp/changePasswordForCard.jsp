<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>تغییر رمز کارت</title>
</head>
<body>
<h2>تغییر رمز کارت</h2>

<form action="changePasswordForCard" method="POST">
    <label for="cardNumber">card Number</label>
    <input type="text" id="cardNumber" name="cardNumber" required><br><br>

    <label for="oldPassword">ole password</label>
    <input type="password" id="oldPassword" name="oldPassword" required><br><br>

    <label for="newPassword">new password</label>
    <input type="password" id="newPassword" name="newPassword" required><br><br>

    <button type="submit">submit</button>
</form>
</body>
</html>
