<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<tag:html title="Edit user">
    <tag:menu/>
    <c:url var="edit" value="/user/edit.html"/>
    <c:choose>
        <c:when test="${not empty user}">
            <form method="post" action="${edit}">
                <div class="form-group">
                    <label for="name"><fmt:message key="userLogin"/></label>
                    <input id="name" type="text" name="login" required value="${user.login}">
                </div>
                <select name="role" required="required">
                        <option value="1"><fmt:message key ="student"/></option>
                        <option value="2"><fmt:message key ="dean"/></option>
                        <option value="0"><fmt:message key ="admin"/></option>
                </select>
                <input type="text" name="id" value="${user.id}" hidden>
                <div class="form-group">
                    <button type="submit"><fmt:message key="send"/></button>
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
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="nothingFounded"/></h1>
            <c:url var="back" value="/user/list.html"/>
            <a href="${back}" class="btn"><fmt:message key="back"/></a>
        </c:otherwise>
    </c:choose>

</tag:html>
