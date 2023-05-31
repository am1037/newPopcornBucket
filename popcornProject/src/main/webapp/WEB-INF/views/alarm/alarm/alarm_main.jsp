<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
        <%--let screenMapStr = '${screenMap}';--%>
        <%--let screenMap = JSON.parse(screenMapStr);--%>
        <%--let favoriteTheaterListStr = '${listTheaterFavorites}';--%>
        <%--let favoriteTheaterList = JSON.parse(favoriteTheaterListStr);--%>
        <%--let movieIdToTitleMapStr = '${movieIdToTitleMap}';--%>
        <%--let movieIdToTitleMap = JSON.parse(movieIdToTitleMapStr);--%>
        <%--let theaterIdToNameMapStr = '${theaterIdToNameMap}';--%>
        <%--let theaterIdToNameMap = JSON.parse(theaterIdToNameMapStr);--%>
        //console.log(screenMap);
    </script>

    <script>
        let moviesNotOnScreen = [];
        let moviesOnScreen = [];
        for(let i of Object.keys(screenMap)){
            if(screenMap[i].length === 0) {
                moviesNotOnScreen.push(i);
            } else {
                moviesOnScreen.push(i);
            }
        }
    </script>

    <style>
    </style>
</head>

<body>
<div id="navbar-container">
    error while loading navbar
</div>
<h3>20230527-20230530까지의 결과</h3>
*실제 크롤링 및 kmdb 데이터입니다.<br>
*서울이랑 경기만 크롤링 되어있음.<br>
*스케줄러는 아직 설정되어있지 않습니다<br>(4일 주기로 -> 일주일에 두 번 정도 크롤링 예정)<br><br><br>
<%--1. 각각 영화에 대해서--%>
<%--2. 각각 극장에 대한--%>
<%--상영횟수를 보여준다--%>
<div id="movie-favorites-onscreen">
    <h2>다음 영화들은 선택해주신 영화관에 상영관이 있답니다!</h2>
        <script>
            console.log(screenMap);
            for(let i of moviesOnScreen){
                for(let j of favoriteTheaterList) {
                    let a = screenMap[i].filter(row => row.theater_id === j);
                    console.log(i + " " + j + " " + a.length);
                }
                $('#movie-favorites-onscreen').append('<div class="movie-favorite-notonscreen">' + movieIdToTitleMap[i] + '</div>');
            }
        </script>
</div>

<div id="movie-favorites-notonscreen">
<h2>다음 영화들은 선택해주신 영화관에 상영관이 없답니다..</h2>
    <script>
        for(let i of moviesNotOnScreen){
            $('#movie-favorites-notonscreen').append('<div class="movie-favorite-notonscreen">'+movieIdToTitleMap[i]+'</div>');
        }
    </script>
</div>
</body>

</html>






