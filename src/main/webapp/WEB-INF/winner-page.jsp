<%--
  Created by IntelliJ IDEA.
  User: glebl
  Date: 10.09.2024
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match Winner</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/winner-style.css">
</head>
<body>
<div class="winner-container">
    <h1>Congratulations!</h1>
    <p class="winner-name">Winner: <span>${match.winner.name}</span></p>
    <div class="final-score">
        <p>Final Score:</p>
        <p>${match.playerOne.name} - ${match.setWinsByPlayerOne} : ${match.playerTwo.name} - ${match.setWinsByPlayerTwo}</p>
    </div>
    <a href="${pageContext.request.contextPath}/new-match" class="new-match-btn">Start New Match</a>
    <a href="${pageContext.request.contextPath}/matches" class="matches-btn">Match History</a>
</div>
</body>
</html>

