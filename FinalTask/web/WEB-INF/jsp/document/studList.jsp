<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="Student documents">
    <tag:menu/>
    <div class="inner">
        <table>
            <c:if test="${not empty documents}">
                <c:forEach items="${documents}" var="item">
                    <c:choose>
                        <c:when test="${item.status eq true}">
                            <c:set var="key" value="ready"/>
                            <tr class="table-success">
                        </c:when>
                        <c:otherwise>
                            <c:set var="key" value="inProcces"/>
                            <tr class="table-warning">
                        </c:otherwise>
                    </c:choose>
                    <th>${item.documentType}</th>
                    <th><fmt:message key="${key}"/></th>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

        <c:url value="/document/order.html" var="link"/>
        <a href="${link}"> <fmt:message key="orderDoc"/> </a>
    </div>

</tag:html>
