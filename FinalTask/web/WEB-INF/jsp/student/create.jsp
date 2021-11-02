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
                    <input id="name" type="text" name="name" required >
                </div>
                <div class="form-group">
                    <label for="lastname"><fmt:message key="secondName"/></label>
                    <input id="lastname" type="text" name="lastname" required>
                </div>
                <div class="form-group">
                    <label for="patronymic"><fmt:message key="Patronymic"/></label>
                    <input id="patronymic" type="text" name="patronymic" required>
                </div>
                <div class="form-group">
                    <label for="login"><fmt:message key="userLogin"/></label>
                    <input id="login" type="text" name="login" required>
                </div>
                <div class="form-group">
                    <label for="birthDate"><fmt:message key="birthDate"/></label>
                    <input id="birthDate" type="text" name="date" required>
                </div>
                <div class="form-group">
                    <label for="mail"><fmt:message key="mail"/></label>
                    <input id="mail" type="email" name="mail" required>
                </div>
                <div class="form-group" hidden>
                    <input id="dean" type="text" name="deanId" required value="${sessionScope.deanId}">
                </div>
                <div class="form-group">
                    <button class="btn" type="submit"><fmt:message key="create"/></button>
                </div>
            </form>
   <tag:warning/>

</tag:html>