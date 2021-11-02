<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<tag:html title="home Page">
    <div class="row">
    <div class="col-9">
        <h1><fmt:message key="greetings"/></h1>
        <br>
        <div class="container" background-color = "lightgrey">
        <p><fmt:message key="greetingInfo"/></p>
        <c:url value="/loginForm.html" var="login"/>
        </div>
        <a href="${login}" class="btn"><fmt:message key="login"/></a><br>
    </div>
        <div class="col-3 btn-group-vertical btn-group-sm" id="lang_form">
            <c:url var="action" value="/.html"/>
            <form method="post" action="${action}" >
                <button class="btn" type="submit" name="language" value="ru_Ru"> Русский</button>
                <button class="btn" type="submit" name="language" value="en_EN"> English</button>
            </form>
        </div>

</tag:html>
