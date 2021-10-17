<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="Create user">
    <tag:menu/>
    <c:choose>
        <c:when test="${not empty universities}">
            <c:url var="create" value="/dean/create.html"/>
            <form method="post" action="${create}">
                <div class="form-group">
                    <label for="name"><fmt:message key="faculty"/></label>
                    <input id="name" type="text" name="faculty" required>
                </div>
                <div class="form-group">
                    <label for="address"><fmt:message key="address"/></label>
                    <input id="address" type="text" name="address" required>
                </div>
                <div class="form-group">
                    <label for="phoneNumber"><fmt:message key="phoneNumber"/></label>
                    <input id="phoneNumber" type="text" name="phoneNumber" required>
                </div>
                <select name="unicId" required="required">
                    <c:forEach items="${universities}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
                <div class="form-group">
                    <button type="submit"><fmt:message key="send"/></button>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="nothingFounded"/></h1>
            <c:url var="back" value="/dean/list.html"/>
            <a href="${back}" class="btn"><fmt:message key="back"/></a>
        </c:otherwise>
    </c:choose>
</tag:html>
