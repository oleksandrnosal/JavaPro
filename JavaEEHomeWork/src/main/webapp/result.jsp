<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Результати опитування</title>
<body>
<h2>Вид спорту:</h2>
<p>Біг:<%= request.getAttribute("runningCount")%></p>
<p>Важка атлетика:<%= request.getAttribute("weightCount")%></p>

<h2>Час занять:</h2>
<p>Ранок:<%= request.getAttribute("morningCount")%></p>
<p>Вечір:<%= request.getAttribute("eveningCount")%></p>

<button type="index.jsp"/>
<a href="index.jsp">Повернутися до опитування</a>
</body>
</html>