<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="alert alert-danger" role="alert">
        빨리 봐야하는 영화에요<br>
        <table class="table">
            <thead>
            <tr>
                <th>제목</th>
                <th>상영 횟수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${redList}">
                <tr>
                    <td>${o}</td>
                    <td>${map[o]}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="alert alert-warning" role="alert">
        천천히 봐도 되는 영화에요<br>
        <table class="table">
            <thead>
            <tr>
                <th>제목</th>
                <th>상영 횟수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${orangeList}">
                <tr>
                    <td>${o}</td>
                    <td>${map[o]}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="alert alert-light" role="alert">
        안 봐도 되는 영화에요<br>
        <table class="table">
            <thead>
            <tr>
                <th>제목</th>
                <th>상영 횟수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${lightList}">
                <tr>
                    <td>${o}</td>
                    <td>${map[o]}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>