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
<div id="anywhere-door">
    <a href="alarm/">alarm</a><br>
    <a href="kakaoTest/">kakaoTest</a><br>
    <button id="btn_send">send message</button><br>
    <script>
        $("#btn_send").click(function () {
            $.ajax({
                url: "admin/sendMessage",
                type: "GET",
                success: function (data) {
                    console.log("success");
                    $("#result").html(data);
                }
            });
        });
    </script>
</div>
<br>
<br>

<div id="be_careful">be careful when you hit these buttons!!!!<br>
    <button id="btn_truncateScreenTable">Truncate screen table</button>
    <button id="btn_emptyFolder">Empty Folder</button><br>
</div>
<br>
<br>

<div id="div1">div for buttons without parameters<br>
    <button id="btn_CheckFiles">Check Existing Files</button>
    <button id="btn_refresh_properties">Refresh Properties</button><br>
    <script>
        $("#btn_refresh_properties").click(function () {
            $.ajax({
                url: "admin/refreshProperties",
                type: "GET",
                success: function (data) {
                    console.log("success");
                    $("#result").html(data);
                }
            });
        });
    </script>
</div>
<br>
<br>

<div id="div2">div for date to date<br>
    <label for="theater">theater</label><input id="theater"><br>
<%--    //나중에 체크 박스 형식으로 바꾸기?<br>--%>
    <label for="region">region</label>
    <select id="region">
        <option value="서울">서울</option>
        <option value="경기">경기</option>
        <option value="강원">강원</option>
        <option value="경상">경상</option>
        <option value="경상">경상</option>
        <option value="전라">전라</option>
        <option value="충청">충청</option>
        <option value="울산">울산</option>
        <option value="광주">광주</option>
        <option value="인천">인천</option>
        <option value="부산">부산</option>
        <option value="대구">대구</option>
        <option value="대전">대전</option>
        <option value="제주">제주</option>
    </select>
    <br>
    <label for="dateFrom">dateFrom</label><input id="dateFrom" value="${today}" placeholder="YYYYMMDD"><br>
    <label for="dateUntil">dateUntil</label><input id="dateUntil" value="${lastday}" placeholder="YYYYMMDD"><br>
    <button id="btn_crawlFromUntilRegion">crawl -> json (from, until, region)</button><br>
    <button id="btn_crawlOne">crawl -> json (from, until, theater)</button><br>
    <button id="btn_insertFromUntil">json -> DB (from, until)</button><br>
    <button id="btn_insertExistingFiles">json -> DB (existing)</button>
</div>
<br>
<br>

<div id="movie-id">
    <button id="btn_movie_id">movie_id(DOCID) validation</button><br>
    <div id="movie-id-error-handling">
        <input id="movie_title" placeholder="movie_title">
        <input id="movie_DOCID" placeholder="movie_DOCID">
        <button id="btn_movie_id_one">movie_id(DOCID) validation one</button>
    </div>
</div>
<br>
<br>

<div id="result">

</div>
<br>
<br>

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
                console.log(data);
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btn_emptyFolder").click(function () {
        $.ajax({
            url: "admin/emptyFolder",
            type: "GET",
            success: function (data) {
                console.log(data);
                //$("#result").html(data); 추후 예외처리
            }
        });
    });

    $("#btn_movie_id").click(function () {
        $.ajax({
            url: "admin/insertMovieId",
            type: "GET",
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            }
        });
    });

    $("#btn_movie_id_one").click(function () {
        $.ajax({
            url: "admin/insertMovieIdOne",
            type: "GET",
            data: {
                "title": $("#movie_title").val(),
                "DOCID": $("#movie_DOCID").val()
            },
            success: function (data) {
                console.log("success");
                $("#result").html(data);
            },
            error: function (data) {
                console.log("error");
                $("#result").html(data);
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
                console.log(data);
            }
        });
    });
</script>
</body>
</html>
