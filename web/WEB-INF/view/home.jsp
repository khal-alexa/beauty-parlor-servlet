<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>Welcome</title>
</head>
<body>
<h1><fmt:message key="welcome.message"/></h1>

<ul>
    <li><a href="<ctags:replaceParameter name="lang" value="en"/>"><fmt:message key="lang.en"/></a><fmt:setLocale value="en_US" scope="session"/></li>
    <li><a href="<ctags:replaceParameter name="lang" value="ua"/>"><fmt:message key="lang.ua"/></a><fmt:setLocale value="uk_UA" scope="session"/></li>
</ul>

<form action="/login" method="get">
    <a href="/login"><fmt:message key="button.login"/></a>
</form>
<form action="/register" method="get">
    <a href="/register"><fmt:message key="button.register"/></a>
</form>
<hr>
<form action="/" method="get">
<h1><fmt:message key="message.guest"/></h1>
<div class="container">

<table style="width:50%" border="2">
<tr>
    <th><fmt:message key="table.column.treatment"/></th>
    <th><fmt:message key="table.column.specialist"/></th>
    <th><fmt:message key="table.column.rating"/></th>
    <th><fmt:message key="table.column.price"/></th>
</tr>
<c:forEach var="treatment" items="${treatments.getItems()}">
    <tr>
    <td><c:out value="${treatment.getTreatmentName()}"/></td>
    <td><c:out value="${treatment.getSpecialistName()}"/></td>
    <td><c:out value="${treatment.getRate()}"/></td>
        <td><c:out value="${treatment.getPrice()}"/></td>
</c:forEach>
    </tr>
</table>

    <form action="/" method="get">
    <nav aria-label="Navigation">
        <ul class="pagination">
            <c:if test="${page != 1}">
                <li class="page-item"><a class="page-link"
                                         href="/?lang=${param.lang}&page=${page-1}">prev</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${treatments.getTotalPages()}" var="i">
                <c:choose>
                    <c:when test="${page eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only"></span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/?lang=${param.lang}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page lt treatments.getTotalPages()}">
                <li class="page-item"><a class="page-link"
                                         href="/?lang=${param.lang}&page=${page+1}">next</a>
                </li>
            </c:if>
        </ul>
    </nav>
    </form>
    <h5><fmt:message key="guest.message"/></h5>
</div>
</form>
</body>
</html>