<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<div class="welcome-container">
    <h1>Welcome to the Tennis Scoreboard</h1>
    <p>Manage your matches, view history, and start new games!</p>
    <div class="button-group">
        <a href="/matches" class="welcome-btn">Match History</a>
        <a href="/new-match" class="welcome-btn">New Match</a>
    </div>
</div>
</body>
</html>
