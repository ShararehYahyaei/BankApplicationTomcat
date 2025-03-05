<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>تغییر رمز عبور</title>
</head>
<body>
<h2>تغییر رمز عبور</h2>

<form action="changePasswordForUser" method="post">
  <label for="oldPassword">رمز عبور فعلی:</label>
  <input type="password" id="oldPassword" name="oldPassword" required>
  <br><br>

  <label for="newPassword">new password :</label>
  <input type="password" id="newPassword" name="newPassword" required>
  <br><br>

  <button type="submit">submit </button>
</form>


<p style="color: green;"><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
<p style="color: red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
</body>
</html>
