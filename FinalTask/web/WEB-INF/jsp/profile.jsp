<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tag:html title="login">
    <tag:menu/>
    <c:choose>
        <c:when test="${authorizedUser.role.value eq 0}">
            <h1><fmt:message key="helloAdmin"/></h1>
        </c:when>
        <c:when test="${authorizedUser.role.value eq 1 or authorizedUser.role.value eq 2}">
            <div class="inner">
                <p><fmt:message key="name"/> : ${student.name} </p>
                <p><fmt:message key="secondName"/>: ${student.lastname}</p>
                <p><fmt:message key="Patronymic"/>: ${student.patronymic}</p>
                <p><fmt:message key="birthDate"/>: ${student.date}</p>
                <p><fmt:message key="mail"/>: ${student.mail}</p>
                <h2><fmt:message key="dean"/></h2>
                <p><fmt:message key="faculty"/>: ${dean.faculty}</p>
                <p><fmt:message key="address"/>: ${dean.address}<br>
                <p><fmt:message key="phoneNumber"/>: ${dean.phoneNumber}</p>
                <p><fmt:message key="university"/>: ${dean.universityName}</p>
            </div>
        </c:when>
    </c:choose>
</tag:html>