<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<tag:html title="Edit user">
    <tag:menu/>
    <c:url var="edit" value="/dean/edit.html"/>
    <c:choose>
        <c:when test="${not empty dean and not empty universities}">
            <form method="post" action="${edit}">
                <div class="form-group">
                    <label for="name"><fmt:message key="faculty"/></label>
                    <input id="name" type="text" name="faculty" required value="${dean.faculty}">
                </div>
                <div class="form-group">
                    <label for="address"><fmt:message key="address"/></label>
                    <input id="address" type="text" name="address" required value="${dean.address}">
                </div>
                <div class="form-group">
                    <label for="phoneNumber"><fmt:message key="phoneNumber"/></label>
                    <input id="phoneNumber" type="text" name="phoneNumber" required value="${dean.phoneNumber}">
                </div>
                <select name="unicId" required="required">
                    <c:forEach items="${universities}" var="item">
                        <c:choose>
                            <c:when test="${universityId eq item.id}">
                                <option value="${item.id}" selected>${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input type="text" name="id" value="${dean.id}" hidden>
                <div class="form-group">
                    <button class="btn" type="submit"><fmt:message key="send"/></button>
                </div>
            </form>
            <tag:warning/>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="nothingFounded"/></h1>
            <c:url var="back" value="/dean/list.html"/>
            <a href="${back}" class="btn"><fmt:message key="back"/></a>
        </c:otherwise>
    </c:choose>

</tag:html>
