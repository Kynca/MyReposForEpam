<%@tag language="java" pageEncoding="utf-8" %>
<%@attribute name="title" required="true" type="java.lang.String" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="properties.languages" scope="session"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
            crossorigin="anonymous"></script>
    <c:url var="style" value="/css/style.css"/>
    <link rel="stylesheet" href="${style}">
    <title>${title}</title>
</head>

<body>
<div class="row">
    <div class="col-3"></div>
    <div id="main_container" class="col-6">
        <jsp:doBody/>
    </div>
    <div class="col-3"></div>
</div>
</body>
</html>
