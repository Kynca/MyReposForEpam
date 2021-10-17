<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<tag:html title="Edit user">
    <tag:menu/>
    <c:url var="edit" value="/student/edit.html"/>
    <c:choose>
        <c:when test="${not empty student}">
            <form method="post" action="${edit}">
                <div class="form-group">
                    <label for="name"><fmt:message key="name"/></label>
                    <input id="name" type="text" name="name" required value="${student.name}">
                </div>
                <div class="form-group">
                    <label for="lastname"><fmt:message key="secondName"/></label>
                    <input id="lastname" type="text" name="lastname" required value="${student.lastname}">
                </div>
                <div class="form-group">
                    <label for="patronymic"><fmt:message key="Patronymic"/></label>
                    <input id="patronymic" type="text" name="patronymic" required value="${student.patronymic}">
                </div>
                <div class="form-group">
                    <label for="birthDate"><fmt:message key="birthDate"/></label>
                    <input id="birthDate" type="text" name="date" required value="${student.date}">
                </div>
                <div class="form-group">
                    <label for="mail"><fmt:message key="mail"/></label>
                    <input id="mail" type="email" name="mail" required value="${student.mail}">
                </div>
                <div class="form-group">
                    <label for="dean"><fmt:message key="deanId"/></label>
                    <input id="dean" type="text" name="deanId" required value="${student.deanId}">
                </div>
                <div class="form-group">
                    <input hidden type="text" name="id" value="${student.id}">
                </div>
                <div class="form-group">
                    <button type="submit"><fmt:message key="send"/></button>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="nothingFounded"/></h1>
            <c:url var="back" value="/student/list.html"/>
            <a href="${back}" class="btn"><fmt:message key="back"/></a>
        </c:otherwise>
    </c:choose>

</tag:html>
