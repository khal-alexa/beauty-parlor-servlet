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
<h1><fmt:message key="message.specialist"/></h1>
<h2><fmt:message key="specialist.cabinet"/></h2>
</body>
</html>
