<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tag:html title="Student marks">
    <tag:menu/>
    <c:choose>
        <c:when test="${not empty marks}">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th><fmt:message key="subjectName"/></th>
                    <th><fmt:message key="marks"/></th>
                    <th><fmt:message key="markDate"/></th>
                </tr>
                <c:forEach items="${marks}" var="item">
                    <tr>
                        <th>${item.subjectName}</th>
                        <th>${item.rate}</th>
                        <th>${item.date}</th>
                    </tr>
                </c:forEach>
                </thead>
            </table>
        </c:when>
        <c:otherwise>
            <fmt:message key = "nothingFounded"/>
        </c:otherwise>
    </c:choose>
</tag:html>