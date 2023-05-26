<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-26
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="i" items="${screenMap.keySet()}">
    console.log("${i} : ${screenMap[i]}");
</c:forEach>