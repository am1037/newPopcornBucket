<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-23
  Time: 오후 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="i" items="${regionSet}">
    <option value="${i}">${i}</option>
</c:forEach>