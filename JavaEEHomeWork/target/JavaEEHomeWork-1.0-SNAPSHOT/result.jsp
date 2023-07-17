<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 15.07.2023
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/submit" method="POST">
<html>
<head>
    <title>Результати</title>

    <% String question1 = (String) session.getAttribute("question1");
        String question2 = (String) session.getAttribute("question2");

         int (answer1) = session.getAttribute("Yes");
        int (answer2) = session.getAttribute("No");
    %>
    <h2>Ви відповіли на питання  <%= question1 %> та <%=question2 %> </h2>
    <h4>На питання "Чи подобається Вам займатися спортом?" відповіли: -  <%= answer1 %> раз</h4>
    <h4>На питання "Чи подобається Вам займатися спортом?" відповіли: -  <%= answer2 %> раз</h4>
    <h4>На питання "Чи подобається Вам такий вид спорту як футбол?" відповіли: -  <%= answer1 %> раз</h4>
    <h4>На питання "Чи подобається Вам такий вид спорту як футбол?" відповіли: -  <%= answer2 %> раз</h4>

</head>
</html>
</form>