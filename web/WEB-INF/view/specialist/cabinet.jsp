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
<form action="/specialist/cabinet" method="get">
    <dl>
        <dt><fmt:message key="date.format"/></dt>
        <dd><input type="date" name="startDate" id="startDate" value="${param.startDate}">
            <a href="/specialist/cabinet?startDate=${param.startDate}"><fmt:message key="button.submit"/></a>
        </dd>
    </dl>
</form>
<form action="/specialist/cabinet" method="post">
    <div class="container">

        <table style="width:50%" border="2">
            <tr>
                <th><fmt:message key="table.column.id"/></th>
                <th><fmt:message key="table.column.time"/></th>
                <th><fmt:message key="table.column.date"/></th>
                <th><fmt:message key="table.column.treatment"/></th>
                <th><fmt:message key="table.column.done"/></th>
            </tr>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td><c:out value="${appointment.getId()}"/></td>
                    <td><c:out value="${appointment.getTimeslot()}"/></td>
                    <td><c:out value="${appointment.getDate()}"/></td>
                    <td><c:out value="${appointment.getTreatmentName()}"/></td>
                    <td><c:out value="${appointment.getDone()}"/></td>
                    <td>
                        <a href="/specialist/cabinet?id=${appointment.getId()}"><fmt:message key="table.column.done"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>
<form action="/logout" method="get">
    <a href="/logout"><fmt:message key="button.logout"/></a>
</form>
</body>
</html>
