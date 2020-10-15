<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Login Form</title>
</head>
<body>
<h2><fmt:message key="login.form"/></h2>
<form action="auth" method="post">
    <div class="container">
        <label><b><fmt:message key="user.login" /></b></label>
        <input type="text" placeholder="<fmt:message key="message.username" />" name="username" required>
        <br>

        <label><b><fmt:message key="user.password" /></b></label>
        <input type="password" placeholder="<fmt:message key="message.password" />" name="password" required>
        <br>
        <button type="submit"><fmt:message key="button.auth" /></button>
    </div>
</form>
</body>
</html>
