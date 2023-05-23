<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-22
  Time: 오후 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--jquery--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <button id="login"> login </button>
    <button id="logout"> logout </button><br>
    <button id="btn"> info </button>

<%--    onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=04b500e325879415373f7b3fa5ff0e86&redirect_uri=https://6d23-115-137-12-120.ngrok-free.app/newPopcornBucket_war_exploded/kakaoLogin&response_type=code'"--%>
    <script>
        // https://kauth.kakao.com/oauth/authorize?client_id=04b500e325879415373f7b3fa5ff0e86&response_type=code&redirect_uri=http://localhost:8080/kakaoTest
        let targetUrl = "${targetUrl}";

        $('#login').click(function (){
            location.href = targetUrl + 'kakaoLogin';
        })

        $('#logout').click(function (){
            location.href = targetUrl + 'kakaoLogout';
        })

        $('#btn').click(function (){
            console.log(new URL(window.location.href).origin + '/newPopcornBucket_war_exploded/kakaoTest/kakaoGetTokenInfo');
            $.ajax({
                url:  new URL(window.location.href).origin + '/newPopcornBucket_war_exploded/kakaoTest/kakaoGetTokenInfo',
                type: 'get',
                success: function (data){
                    console.log(data);
                }
            })
        })


    </script>

</body>
</html>
