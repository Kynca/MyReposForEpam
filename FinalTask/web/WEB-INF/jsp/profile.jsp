<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tag:html title="login">
    <tag:menu/>
    <c:choose>
        <c:when test="${authorizedUser.role.value eq 0}">
            <h1><fmt:message key = "helloAdmin"/></h1>
        </c:when>
        <c:when test="${authorizedUser.role.value eq 1 or authorizedUser.role.value eq 2}">
            <div class="row">
                <div class="col-3"/>
                <div class="col-6" >
                    <fmt:message key = "name"/><br>
                        ${student.name}<br>
                    <fmt:message key = "secondName"/><br>
                        ${student.lastname}<br>
                    <fmt:message key = "Patronymic"/><br>
                        ${student.patronymic}<br>
                    <fmt:message key = "birthDate"/><br>
                        ${student.date}<br>
                    <fmt:message key = "mail"/><br>
                        ${student.mail}<br>
                    <h2><fmt:message key ="dean"/></h2>
                    <fmt:message key = "faculty"/><br>
                    ${dean.faculty}<br>
                    <fmt:message key = "address"/><br>
                        ${dean.address}<br>
                    <fmt:message key = "phoneNumber"/><br>
                        ${dean.phoneNumber}<br>
                    <fmt:message key = "university"/><br>
                        ${dean.universityName}<br>
                </div>
            </div>
        </c:when>
    </c:choose>
</tag:html>