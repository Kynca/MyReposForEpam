<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="Student documents">
    <tag:menu/>
    <div class="container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="document"/></th>
                <th><fmt:message key="receiverName"/></th>
                <th><fmt:message key="receiverMail"/></th>
                <th><fmt:message key="comments"/></th>
            </tr>
            </thead>
            <c:if test="${not empty documents}">
                <c:forEach items="${documents}" var="item">
                    <tr>
                    <th>${item.documentType}</th>
                    <th>${item.receiverName}</th>
                    <th>${item.receiverMail}</th>
                    <th>${item.comment}</th>
                    <c:url var="action" value="/document/edit.html"/>
                        <th><form method="post" action="${action}">
                            <input name="path" type="file" required>
                            <button type="submit" name="id" value="${item.id}"><fmt:message key="edit"/> </button>
                        </form>
                        </th>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</tag:html>
