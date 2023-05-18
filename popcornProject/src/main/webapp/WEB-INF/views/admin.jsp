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
    <button id="btncff">Check From existing Files</button>
    <button id="btntrunc">Truncate screen table</button>
<%--<button id="emptyJsonFolderBtn">empty json folder</button>--%>
</div>
<div id="div2">div for date to date<br>
    <label for="theater">theater</label><input id="theater"><br>
    <label for="dateFrom">dateFrom</label><input id="dateFrom" value="${today}"><br>
    <label for="dateUntil">dateUntil</label><input id="dateUntil" value="${lastday}"><br>
    <button id="btnc2j">Crawl2Json</button>
    <button id="btncat">CrawlAllTheaters</button><br>
    <button id="btnj2d">Json2DB</button>
    <button id="btniat">InsertAllTheaters</button><br>
</div>
<div id="result">

</div>
<script>
    $("#btncff").click(function () {
        $.ajax({
            url: "ccff",
            type: "GET",
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            }
        });
    });

    $("#btnc2j").click(function () {
        var theater = $("#theater").val();
        var dateFrom = $("#dateFrom").val();
        var dateUntil = $("#dateUntil").val();
        $.ajax({
            url: "ccbtafu",
            type: "GET",
            data: {
                theater: theater,
                dateFrom: dateFrom,
                dateUntil: dateUntil
            },
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btncat").click(function () {
        var dateFrom = $("#dateFrom").val();
        var dateUntil = $("#dateUntil").val();
        $.ajax({
            url: "ccat",
            type: "GET",
            data: {
                dateFrom: dateFrom,
                dateUntil: dateUntil
            },
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btniat").click(function () {
        var dateFrom = $("#dateFrom").val();
        var dateUntil = $("#dateUntil").val();
        $.ajax({
            url: "ciat",
            type: "GET",
            data: {
                dateFrom: dateFrom,
                dateUntil: dateUntil
            },
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btnj2d").click(function () {
        var theater = $("#theater").val();
        var dateFrom = $("#dateFrom").val();
        var dateUntil = $("#dateUntil").val();
        $.ajax({
            url: "ci2db",
            type: "GET",
            data: {
                theater: theater,
                dateFrom: dateFrom,
                dateUntil: dateUntil
            },
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btntrunc").click(function () {
        $.ajax({
            url: "truncateScreen",
            type: "GET",
            success: function (data) {
                console.log("success");
                //$("#result").html(data); 추후 예외처리
            }
        });
    });
</script>
</body>
</html>
