<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<tag:html title="login">
    <c:url value="login.html" var = "loginUrl"/>
    <form method="post" action="${loginUrl}"  >
        <div class="form-group">
            <label for="login"><fmt:message key="userLogin" /></label>
            <input type="text" id = "login" placeholder="<fmt:message key="loginEnter" />" required="required" name = "login">
        </div>

        <div class="form-group">
            <label for="pass"><fmt:message key="userPass" /></label>
            <input type="password" id = "pass" placeholder="<fmt:message key="passEnter" />" required="required" name = "password">
        </div>
        <button type="submit"><fmt:message key="authorise" /></button>
    </form>
</tag:html>