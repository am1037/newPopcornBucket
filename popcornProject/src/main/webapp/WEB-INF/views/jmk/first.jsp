<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-09
  Time: 오전 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.4.js"></script>
</head>
<body>
this is page for jmk
<input id="theater_code">
<input id="date">
<button id="btn">click</button>
<br><br>
<button id="btn2" onclick="location.href='${pageContext.request.contextPath}/alarm/kakaotest.jsp'">click2</button>
<script>
    $('#btn').click(function () {
        var theater_code = $('#theater_code').val();
        var date = $('#date').val();
        $.ajax({
            url: 'crawling',
            type: 'get',
            data: {
                theater_code: theater_code,
                date: date
            },
            success: function (data) {
                console.log(data);
            }
        })
    })
</script>

</body>
</html>
