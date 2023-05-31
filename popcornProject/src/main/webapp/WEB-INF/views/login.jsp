<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-27
  Time: 오전 5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/css4jmk.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://accounts.google.com/gsi/client" async defer></script>

    <!-- jquery -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.4.js"></script>

    <script>
        $.ajax({
            url: "${pageContext.request.contextPath}/alarm/navbar/",
            type: "GET",
            success: function (data) {
                $('#navbar-container').html(data);
            },
            error: function (e) {
                console.log("error while getting navbar")
                console.log(e);
            }
        });
    </script>
</head>

<body>
<div id="navbar-container">
    error while loading navbar
</div>

<div class="container-fluid pt-5 text-center">
this is login page<br>
<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=04b500e325879415373f7b3fa5ff0e86&redirect_uri=http://localhost:8887/newPopcornBucket_war_exploded/stepThree">
    <img src="${pageContext.request.contextPath}/resources/kakao_login_medium_narrow.png"><br><br><br><br>
</a>
등록을 마치지 않으신 분은<br>
다음 QR을 스캔하여 식별을 위한 라인 및 카카오 ID 등록을 진행해주세요.<br>
라인ID는 알림을 위해 사용되며 카카오ID는 개인 식별을 위해 사용됩니다.<br><br>
    <img src="${pageContext.request.contextPath}/resources/lineQR.png">
</div>


</body>
</html>
