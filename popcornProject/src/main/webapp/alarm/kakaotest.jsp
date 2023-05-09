<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-05-09
  Time: 오후 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/kakao.js"></script>
</head>
<body>
    <button id="kakao-login-btn">카카오 로그인</button>
    <script>
        const REST_API_KEY = "04b500e325879415373f7b3fa5ff0e86";
        const REDIRECT_URI = "http://localhost:8887"+'<%= request.getContextPath() %>'+'/';
        console.log(REDIRECT_URI)
        // $('#kakao-login-btn').click(function (){
        //     $.ajax(
        //         {
        //             url: 'https://kauth.kakao.com/oauth/authorize',
        //             type: 'GET',
        //             data: {
        //                 client_id : REST_API_KEY,
        //                 redirect_uri: REDIRECT_URI,
        //                 response_type: 'code'
        //             },
        //             success: function (data){
        //                 console.log(data)
        //             }
        //         }
        //     )
        //     }
        )
    </script>
</body>
</html>
