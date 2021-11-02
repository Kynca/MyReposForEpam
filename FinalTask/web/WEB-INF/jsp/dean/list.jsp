<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="Dean list">
    <tag:menu/>
    <h2><fmt:message key="deanList"/></h2>
    <div class="inner">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="faculty"/></th>
            <th><fmt:message key="phoneNumber"/></th>
            <th><fmt:message key="address"/></th>
            <th><fmt:message key="university"/></th>
        </tr>
        </thead>
        <c:forEach items="${deans}" var="item">
        <tr>
            <th>${item.id}</th>
            <th>${item.faculty}</th>
            <th>${item.phoneNumber}</th>
            <th>${item.address}</th>
            <th>${item.universityName}</th>
        </tr>
        </c:forEach>
    </table>
    </div>
    <c:url value="/dean/process.html" var="process"/>

    <form action="${process}" method="post">
        <label for="id">Id</label>
        <input id="id" required="required" type="text" name="id"><br>
        <input type="radio" id="edit" name="action" value="false" required="required">
        <label for="edit"><fmt:message key="edit"/></label><br>
        <input type="radio" id="delete" name="action" value="true">
        <label for="delete"><fmt:message key="delete"/></label><br>
        <button class="btn" type="submit"><fmt:message key="send"/></button>
    </form>

    <c:url value="/dean/find/uni.html" var="findUni"/>
    <a href="${findUni}" class="btn"><fmt:message key="create"/></a>

</tag:html>
