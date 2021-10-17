<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="User list">
    <tag:menu/>
    <h2><fmt:message key="userList"/></h2>


            <table class="table table-bordered" id="table_id">
                <thead>
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="role"/></th>
                </tr>
                </thead>
    <c:forEach items="${users}" var="item">
                <tr>
                    <th>${item.id}</th>
                    <th>${item.login}</th>
                    <th>${item.role.value}</th>
                </tr>
    </c:forEach>
            </table>

    <c:url value="/user/process.html" var="process"/>

    <form action="${process}" method="post">
        <label for="id">Id</label>
        <input id="id" required="required" type="text" name="id"><br>
        <input type="radio" id="edit" name="action" value="false" required="required">
        <label for="edit"><fmt:message key="edit"/></label><br>
        <input type="radio" id="delete" name="action" value="true">
        <label for="delete"><fmt:message key="delete"/></label><br>
        <button type="submit"><fmt:message key="send"/></button>
    </form>

</tag:html>
