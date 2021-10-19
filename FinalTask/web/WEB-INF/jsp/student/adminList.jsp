<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="Student list">
    <tag:menu/>
    <h2><fmt:message key="students"/></h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="secondName"/></th>
            <th><fmt:message key="Patronymic"/></th>
            <th><fmt:message key="birthDate"/></th>
            <th><fmt:message key="mail"/></th>
            <th><fmt:message key="university"/></th>
            <th><fmt:message key="dean"/></th>

        </tr>
        <c:forEach items="${studentsInfo}" var="item">
        <tr>
            <th>${item.key.id}</th>
            <th>${item.key.name}</th>
            <th>${item.key.lastname}</th>
            <th>${item.key.patronymic}</th>
            <th>${item.key.date}</th>
            <th>${item.key.mail}</th>
            <th>${item.value.universityName}</th>
            <th>${item.value.faculty}</th>
        </tr>
        </c:forEach>
    </table>

    <c:url value="/student/process.html" var="process"/>


    <form action="${process}" method="post">
        <input required="required" type="text" name="id"><br>
        <input type="radio" id="edit" name="action" value="false" required="required">
        <label for="edit"><fmt:message key="edit"/></label><br>
        <input type="radio" id="delete" name="action" value="true">
        <label for="delete"><fmt:message key="delete"/></label><br>
        <input type="submit">
    </form>
    <c:if test="${not empty incorrectData}">
        <div class="container mt-3">
        <div class="toast show">
            <div class="toast-header">
                <strong class="me-auto">Toast Header</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
            </div>
            <div class="toast-body">
                <p class="text-danger"><fmt:message key="incorrectData"/></p>
            </div>
        </div>
    </c:if>

</tag:html>