<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Опитування</title>
</head>
<body>
<form method="POST" action="/check">

            <h1>Якій із запропонованих нижче дисциплін Ви віддаєте перевагу?</h1>
    <li><label>Біг</label>
        <input type="checkbox" name="discipline" value="running"></li>
    <li><label>Силові тренування</label>
        <input type="checkbox" name="discipline" value="weigh training"></li>

            <h1> Який час для тренувань підходить саме для Вас?</h1>
            <li><label>Ранок</label>
                <input type="checkbox" name="day" value="morning"></li>
            <li><label>Вечір</label>
                <input type="checkbox" name="day"  value="evening"></li>

            <button type="submit"/>
            <a href="submit">Надіслати</a>
</form>
</body>
</html>