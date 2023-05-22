<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-16
  Time: 오전 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<div id="div1">div for buttons without parameters<br>
    <button id="btn_CheckFiles">Check Existing Files</button>
    <button id="btn_truncateScreenTable">Truncate screen table</button>
    <button id="btn_emptyFolder">Empty Folder</button>
</div>
<div id="div2">div for date to date<br>
    <label for="theater">theater</label><input id="theater"><br>
    나중에 체크 박스 형식으로 바꾸기?<br>
    <label for="region">region</label>
    <select id="region">
        <option value="서울특별시">서울특별시</option>
        <option value="경기도">경기도</option>
        <option value="강원도">강원도</option>
        <option value="경상북도">경상북도</option>
        <option value="경상남도">경상남도</option>
        <option value="전라북도">전라북도</option>
        <option value="전라남도">전라남도</option>
        <option value="충청북도">충청북도</option>
        <option value="충청남도">충청남도</option>
        <option value="울산광역시">울산광역시</option>
        <option value="광주광역시">광주광역시</option>
        <option value="인천광역시">인천광역시</option>
        <option value="부산광역시">부산광역시</option>
        <option value="대구광역시">대구광역시</option>
        <option value="대전광역시">대전광역시</option>
        <option value="제주특별자치도">제주특별자치도</option>
        <option value="세종특별자치시">세종특별자치시</option>
    </select>
    <br>
    <label for="dateFrom">dateFrom</label><input id="dateFrom" value="${today}"><br>
    <label for="dateUntil">dateUntil</label><input id="dateUntil" value="${lastday}"><br>
    <button id="btn_crawlFromUntilRegion">crawl -> json (from, until, region)</button><br>
    <button id="btn_crawlOne">crawl -> json (from, until, theater)</button><br>
    <button id="btn_insertFromUntil">json -> DB (from, until)</button>
    <button id="btn_insertExistingFiles">json -> DB (existing)</button>
</div>
<div id="result">

</div>
<script>
    $("#btn_CheckFiles").click(function () {
        $.ajax({
            url: "admin/CheckFiles",
            type: "GET",
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            }
        });
    });

    $("#btn_truncateScreenTable").click(function () {
        $.ajax({
            url: "admin/truncateScreenTable",
            type: "GET",
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btn_emptyFolder").click(function () {
        $.ajax({
            url: "admin/emptyFolder",
            type: "GET",
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btn_crawlFromUntilRegion").click(function () {
        $.ajax({
            url: "admin/crawlFromUntilRegion",
            type: "GET",
            data: {
                "dateFrom": $("#dateFrom").val(),
                "dateUntil": $("#dateUntil").val(),
                "region": $("#region").val()
            },
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            }
        });
    });

    $("#btn_crawlOne").click(function () {
        $.ajax({
            url: "admin/crawlOne",
            type: "GET",
            data: {
                "dateFrom": $("#dateFrom").val(),
                "dateUntil": $("#dateUntil").val(),
                "theater": $("#theater").val()
            },
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            }
        });
    });

    $("#btn_insertFromUntil").click(function () {
        $.ajax({
            url: "admin/insertFromUntil",
            type: "GET",
            data: {
                "dateFrom": $("#dateFrom").val(),
                "dateUntil": $("#dateUntil").val()
            },
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            }
        });
    });

    $("#btn_insertExistingFiles").click(function () {
        $.ajax({
            url: "admin/insertExistingFiles",
            type: "GET",
            success: function (data) {
                console.log("success");
            }
        });
    });
</script>
</body>
</html>
