<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="alert alert-danger" role="alert">
        빨리 봐야하는 영화에요<br>
<c:forEach var="r" items="${redList}">
        ${r} : ${map[r]}<br>
</c:forEach>
    </div>

    <div class="alert alert-warning" role="alert">
        천천히 봐도 되는 영화에요<br>
<c:forEach var="o" items="${orangeList}">
            ${o} : ${map[o]}<br>
</c:forEach>
    </div>

    <div class="alert alert-light" role="alert">
        안 봐도 되는 영화에요<br>
<c:forEach var="l" items="${lightList}">
            ${l} : ${map[l]}<br>
</c:forEach>
    </div>