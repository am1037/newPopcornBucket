<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-25
  Time: 오전 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <div class="container-fluid" id="navbar-container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/logo.png" alt="logo" style="width:40px;">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse bg-dark navbar-dark" id="collapsibleNavbar">
            <div class="row container-fluid">
                <ul class="navbar-nav col">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/alarm/theater/">극장 등록</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/alarm/movie/">영화 등록</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/alarm/alarm">알람 관리</a>
                    </li>
                </ul>
                <a href="mypage" class="col text-end">test</a>
            </div>
        </div>
    </div>
</nav>