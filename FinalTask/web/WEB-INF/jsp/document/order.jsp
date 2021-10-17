<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tag:html title="order document">
    <tag:menu/>
    <c:url value="/document/order/add.html" var="documentList"/>
    <form method="post" action="${documentList}">
    <c:choose>
        <c:when test="${not empty docType}">
            <div class="form-group">
                <label>
                    <select name="docType" required="required">
                        <c:forEach items="${docType}" var="item">
                            <option value=${item.typeId}> ${item.documentType}</option>
                        </c:forEach>
                    </select>
                </label><br>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio2" name="deliveryType" value="false" checked>
                <label class="form-check-label" for="radio2">Электронно</label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio1" name="deliveryType" value="true" checked>
                <label class="form-check-label" for="radio1">Самовывоз</label>
            </div>
            <div class="form-group">
                <label for="receiverName"><fmt:message key="receiverName"/></label><br>
                <input id="receiverName" type="text"
                       placeholder="<fmt:message key="enter"/> <fmt:message key="receiverName"/>"
                       name="receiverName">
            </div>
            <div class="form-group">
                <label for="receiverMail"><fmt:message key="receiverMail"/></label><br>
                <input id="receiverMail" type="email"
                       placeholder="<fmt:message key="enter"/> <fmt:message key="receiverMail"/>"
                       name="receiverMail">
            </div>
            <div class="form-group">
                <label for="comment"><fmt:message key="comments"/></label><br>
                <input id="comment" type="text" placeholder="<fmt:message key="enter"/> <fmt:message key="comments"/>"
                       name="comment">
            </div>
            <div class="form-group">
                <button type="submit"><fmt:message key="authorise"/></button>
            </div>
            </form>
        </c:when>
        <c:otherwise>
            <h1>Something goes wrong try again later</h1>
            <c:url var="back" value="studList.jsp"/>
            <a href="${back}" class="btn"><fmt:message key="back"/></a>
        </c:otherwise>
    </c:choose>
</tag:html>
