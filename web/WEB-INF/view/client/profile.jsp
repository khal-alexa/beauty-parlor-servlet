<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>
<h1><fmt:message key="message.client"/></h1>
<h2><fmt:message key="message.client.appointment"/></h2>
<br>
<form action="/client/booking/manicure" method="get">
    <a href="@{/client/booking/{treatmentName}(treatmentName=manicure)}"><fmt:message key="button.manicure"/></a>
</form>
<form action="/client/booking/haircut" method="get">
    <a href="@{/client/booking/{treatmentName}(treatmentName=haircut)}"><fmt:message key="button.haircut"/></a>
</form>
<form action="/client/booking/massage" method="get">
    <a href="@{/client/booking/{treatmentName}(treatmentName=massage)}"><fmt:message key="button.massage"/></a>
</form>
<h2><fmt:message key="message.client.feedback"/></h2>
<form action="${pageContext.request.contextPath}/client/feedback" method="get">
    <a href="/client/feedback"><fmt:message key="button.feedback"/></a>
</form>
<br>
<form action="/logout" method="get">
    <a href="/logout"><fmt:message key="button.logout"/></a>
</form>
</body>
</html>