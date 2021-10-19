<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<tag:html title="Edit user">
    <tag:menu/>
    <c:url var="create" value="/student/create.html"/>
            <form method="post" action="${create}">
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
                    <button type="submit"><fmt:message key="create"/></button>
                </div>
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