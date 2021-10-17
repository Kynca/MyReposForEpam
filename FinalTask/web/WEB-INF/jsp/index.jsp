<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<tag:html title="home Page">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="container">
                <h1><fmt:message key="greetings"/></h1>
                <textarea><fmt:message key="greetingInfo"/></textarea>
                <c:url value="/loginForm.html" var="login"/>
                <a href="${login}" class="btn"><fmt:message key="login"/></a>
            </div>
        </div>
        <div class="col-2">
            <div class="btn-group-vertical btn-group-sm">
                <a href="index.jsp" class="btn" role="button"> Русский</a>
                <a href="index.jsp" class="btn" role="button"> Кнопка</a>
            </div>
        </div>
    </div>
</tag:html>
