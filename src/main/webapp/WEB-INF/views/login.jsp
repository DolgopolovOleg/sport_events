<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html>
<head>
    <title></title>
</head>
<body>
    <form action="/authentication" method="post">

        <p>
            <label for="username">Login: </label>
            <input type="username" id="username" name="username" />
        </p>

        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" />
        </p>
        <p>
            <input type="checkbox" name="_spring_security_remember_me" id="rememberMe"/>
            <label for="rememberMe">Запомнить меня</label>
        </p>

        <p>
            <input type="submit" value="Login" />
        </p>

        <c:if test="${param.error == 'invalidLogin'}">
            <p style="color: red;">Invalid login</p>
        </c:if>

        <c:if test="${param.error == 'loginRequired'}">
            <p style="color: blue;">Invalid login</p>
            Login required!
        </c:if>
    </form>
</body>
</html>
