<%--
  Created by IntelliJ IDEA.
  User: 정민기
  Date: 2023-06-01
  Time: 오후 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<%--jquery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
${memberVO_member_id}<br>
${memberVO_kakao_id}<br>
${memberVO_line_id}<br>
<br>

<c:forEach var="i" items="${favoriteMovieMap.keySet()}">
    <button class="btn_movie" id="btn_${i}">${favoriteMovieMap.get(i)}</button><br>
</c:forEach><br>

<c:forEach var="i" items="${favoriteTheaterMap.keySet()}">
    <button class="btn_theater" id="btn_${i}">${favoriteTheaterMap.get(i)}</button><br>
</c:forEach><br>

<button id="btn">btn</button>
<script>
    let btns = document.querySelectorAll('.btn_movie');

    for (let btnId of btns){
        $(btnId).click(function (){
            console.log(btnId.id);
            let movieId = btnId.id.split('_')[1];
            $.ajax({
                url: 'btnInTest2',
                type: 'get',
                data: {
                    movieId: movieId
                },
                success: function (data){
                    alert(data);
                }
            })
        })
    }

    $.ajax({
        url: "naverMap",
        type: "GET",
        data: {
            "address" : "서울시 강남구 역삼동"
        },
        success: function (data) {
            console.log(data);
        }
    });
</script>

</body>
</html>
