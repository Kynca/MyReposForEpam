<%@tag language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header">
    <c:if test="${not empty authorizedUser}">
        <c:set var="identity" value="${identity}"/>
        <ul class="nav">
            <c:forEach items="${menu}" var="item">
                <c:url value="${item.url}" var="itemUrl"/>
                <li class="item">
                    <a class="nav-link" href="${itemUrl}">
                        <fmt:message key = "${item.name}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>