<%--
  Created by IntelliJ IDEA.
  User: glebl
  Date: 09.09.2024
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Match</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newMatch.css">
</head>
<body>
<div class="container">
    <h2>Start a New Match</h2>
    <form action="${pageContext.request.contextPath}/new-match" method="post" class="form">
        <div class="form-group">
            <label for="playerOne">Player 1 Name:</label>
            <input type="text" name="playerOne" id="playerOne" required>
        </div>
        <div class="form-group">
            <label for="playerTwo">Player 2 Name:</label>
            <input type="text" name="playerTwo" id="playerTwo" required>
        </div>
        <button type="submit" class="submit-btn">Start Match</button>
    </form>
</div>
</body>
</html>