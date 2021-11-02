<%@tag language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.incorrectData}">
    <div class="container mt-3">
        <div class="toast show">
            <div class="toast-header">
                <strong class="me-auto">Can not execute request</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
            </div>
            <div class="toast-body">
                <p class="text-danger">${sessionScope.incorrectData}</p>
            </div>
        </div>
</c:if>