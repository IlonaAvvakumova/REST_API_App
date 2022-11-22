<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Здравствуйте!</title>
</head>
<body>
Если вы хотите начать работу с базой данных пользователей - <br>
нажмите кнопку ниже:

<form action = "users" method="get">
    <input type="submit" value="Начать работу с базой данных">
</form>

<% String param2 = req.getParameter("param2"); %>
<%= param2 + " two parametr" %>

</body>
</html>
