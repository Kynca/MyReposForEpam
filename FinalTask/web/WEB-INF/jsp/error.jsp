<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tag:html title="Error">
    <c:choose>
        <c:when test="${not empty error}">
            <H2>${error}</H2>
        </c:when>
        <c:when test="${not empty pageContext.errorData.requestURI}">
            <H2>Запрошенная страница ${pageContext.errorData.requestURI} не найдена на сервере</H2>
        </c:when>
        <c:otherwise>Непредвиденная ошибка приложения</c:otherwise>
    </c:choose>
    <c:url value="/.html" var = "homePage"/>
    <a href="${homePage}"><fmt:message key="returnMainPage"/> </a>
</tag:html>
