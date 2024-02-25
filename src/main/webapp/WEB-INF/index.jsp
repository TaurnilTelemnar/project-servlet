<%@ page import="com.tictactoe.entity.Sign" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Tic-Tac-Toe</title>
    <link href="../static/main.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<h1>Tic-Tac-Toe</h1>
<table>
    <tr>
        <td onclick="window.location='/logic?click=0'">${sessionScope.service.getCell(0).sign.getSign()}</td>
        <td onclick="window.location='/logic?click=1'">${sessionScope.service.getCell(1).sign.getSign()}</td>
        <td onclick="window.location='/logic?click=2'">${sessionScope.service.getCell(2).sign.getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=3'">${sessionScope.service.getCell(3).sign.getSign()}</td>
        <td onclick="window.location='/logic?click=4'">${sessionScope.service.getCell(4).sign.getSign()}</td>
        <td onclick="window.location='/logic?click=5'">${sessionScope.service.getCell(5).sign.getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=6'">${sessionScope.service.getCell(6).sign.getSign()}</td>
        <td onclick="window.location='/logic?click=7'">${sessionScope.service.getCell(7).sign.getSign()}</td>
        <td onclick="window.location='/logic?click=8'">${sessionScope.service.getCell(8).sign.getSign()}</td>
    </tr>
</table>
<hr>
<c:set var="CROSSES" value="<%=Sign.CROSS%>"/>
<c:set var="NOUGHTS" value="<%=Sign.NOUGHT%>"/>

<c:if test="${sessionScope.winner == CROSSES}">
    <h1>CROSSES WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${sessionScope.winner == NOUGHTS}">
    <h1>NOUGHTS WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${sessionScope.draw}">
    <h1>IT'S A DRAW</h1>
    <br>
    <button onclick="restart()">Start again</button>
</c:if>
<script>
    function restart() {
        $.ajax({
            url: '/restart',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.reload();
            }
        });
    }
</script>

</body>
</html>