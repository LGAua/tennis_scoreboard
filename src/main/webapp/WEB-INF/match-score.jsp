<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Табло теннисного матча</title>
    <link rel="stylesheet" type="text/css" href="/css/scoreboard-style.css">
</head>
<body>
<div class="scoreboard">
    <h2>Табло теннисного матча</h2>
    <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="post">
        <div class="player-score">
            <div class="player-info">${match.playerOne.name}</div>
            <div class="score">Очки: ${match.scorePlayerOne}</div>
            <div class="stats-container">
                <div class="set">
                    <label>Сеты</label>
                    <div>${match.setWinsByPlayerOne}</div>
                </div>
                <div class="game">
                    <label>Игры</label>
                    <div>${match.gameWinsByPlayerOne}</div>
                </div>
            </div>
            <button type="submit" name="playerOne" value="true">Добавить очко</button>
        </div>

        <div class="player-score">
            <div class="player-info">${match.playerTwo.name}</div>
            <div class="score">Очки: ${match.scorePlayerTwo}</div>
            <div class="stats-container">
                <div class="set">
                    <label>Сеты</label>
                    <div>${match.setWinsByPlayerTwo}</div>
                </div>
                <div class="game">
                    <label>Игры</label>
                    <div>${match.gameWinsByPlayerTwo}</div>
                </div>
            </div>
            <button type="submit" name="playerTwo" value="true">Добавить очко</button>
        </div>
    </form>
</div>
</body>
</html>
