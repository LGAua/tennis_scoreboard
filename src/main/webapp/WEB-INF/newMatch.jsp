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
    <title>new match</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/new-match" method="post">
        <label for="playerOne">Имя игрока 1:
            <input type="text" name="playerOne" id="playerOne" required>
        </label><br>
        <label for="playerTwo">Имя игрока 2:
            <input type="text" name="playerTwo" id="playerTwo" required>
        </label><br>
        <button type="submit">Start</button>
    </form>
</body>
</html>
