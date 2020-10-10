<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Registration</title>
</head>
<body>
<h2><fmt:message key="register.form"/></h2>
<form action="/register" method="post">
    <div class="container">
        <label ><b><fmt:message key="user.login"/></b></label>
        <input type="text" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,19}$"
               placeholder="<fmt:message key="message.username"/>" name="username" required>
        <br>
        Username can contain only english letters or numbers or symbols: "_", "-", "." (min - 4, max - 20),
        should start from letter
        <br>

        <label ><b><fmt:message key="user.password"/> </b></label>
        <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$"
               placeholder="<fmt:message key="message.password"/>" name="password" required>
        <br>
        Password requirements:
        - Minimum 8 characters
        - At least one uppercase character
        - At least one lowercase character
        - At least one digit
        <br>

        <label ><b><fmt:message key="confirm.password"/></b></label>
        <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$"
               placeholder="<fmt:message key="confirm.password"/>" name="confirmedPassword" required>
        <br>

        <label ><b><fmt:message key="user.firstname"/></b></label>
        <input type="text" placeholder="<fmt:message key="message.first.name"/>" name="firstName" required>
        <br>

        <label ><b><fmt:message key="user.lastname"/></b></label>
        <input type="text" placeholder="<fmt:message key="message.last.name"/>" name="lastName" required>
        <br>

        <label ><b><fmt:message key="user.email"/></b></label>
        <input type="email" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$"
               placeholder="<fmt:message key="message.email"/>" name="email" required>
        <br>

        <label ><b><fmt:message key="user.phone.number"/></b></label>
        <input type="text" pattern="^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$"
               placeholder="<fmt:message key="message.phone.number"/>" name="phoneNumber" required>
        <br>

        <button type="submit"><fmt:message key="button.register"/></button>
    </div>

</form>
</body>
</html>
