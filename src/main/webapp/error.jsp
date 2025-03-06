<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fa">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error Page</title>
</head>
<body>
<h2>error</h2>
<p>${error}</p>

    <c:forEach var="er" items="${errorList}">
        <p>${er}</p>
    </c:forEach>

<a href="index.jsp">Back to Create Home Page</a>
</body>
</html>
