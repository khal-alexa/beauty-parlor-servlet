<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Feedback</title>
</head>
<body>
<h1><fmt:message key="feedback.message"/></h1>
<form method="POST" action='/client/feedback'>
    <div class="container">
        <label><b><fmt:message key="feedback.specialist.name"/></b></label>
        <select name="specialistName" id="specialistName">
            <c:forEach var="specialistName" items="${specialists}">
                <option value="${specialistName}">
                    <c:out value="${specialistName}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <label><b><fmt:message key="feedback.rate"/></b></label>
        <select name="rate" id="rate">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5" selected>5</option>
        </select>
        <br>
        <label><b><fmt:message key="feedback.form"/></b></label>
        <br>
        <textarea rows="5" cols="60" maxlength="2000" name="text">
         </textarea><br>
        <button type="submit"><fmt:message key="button.send"/></button>
    </div>
</form>
</body>
</html>
