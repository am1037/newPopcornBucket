<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-16
  Time: 오전 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:set var="filesPrefix" value="${filesPrefix}"/>--%>
<%--<c:forEach var="p" items="${filesPrefix}">--%>
<%--    ${p}<br/>--%>
<%--    <c:forEach var="i" items="${filesMap[]}">--%>

<%--    </c:forEach>--%>
<%--</c:forEach>--%>

<c:forEach var="i" items="${theaterCodeVOList}">
    ${i.theater_id} ${i.theater_name}<br/>
</c:forEach>