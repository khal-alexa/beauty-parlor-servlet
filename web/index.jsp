<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>Welcome</title>
</head>
<body>
<h1><fmt:message key="welcome.message"/></h1>

<ul>
  <li><a href="?lang=en"><fmt:message key="lang.en" /></a><fmt:setLocale value="en_US" scope="session"/> </li>
  <li><a href="?lang=ua"><fmt:message key="lang.ua" /></a><fmt:setLocale value="uk_UA" scope="session"/></li>
</ul>

<form action="/login" method="get">
    <a href="/login"><fmt:message key="button.login" />
</form>
<form action="/register" method="get">
    <a href="/register"><fmt:message key="button.register" />
</form>
</body>
</html>
