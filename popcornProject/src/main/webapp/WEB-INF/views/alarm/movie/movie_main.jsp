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
        })
    </script>

    <style>
    </style>
</head>

<body>
    <div id="navbar-container">
        error while loading navbar
    </div>

    <h3>영화 ID가 빈 칸이면 안 됩니다!! 다 제가 못난 탓 입니다!!</h3>

    <div id="movie-onscreen">
        <table>
            <th>영화 제목</th>
            <th>영화 ID</th>
            <th></th>
            <th></th>
                <c:forEach var="i" items="${movieOnScreen.keySet()}">
                    <tr>
                        <td>${movieOnScreen.get(i)}</td>
                        <td>${i}</td>
                        <td><a href="상세정보">상세 정보</a></td>
                        <td><input type="checkbox" id="checkbox_${i}"></td>
                    </tr>
                </c:forEach>
        </table>
        <button id="btn-apply">적용</button>
    </div>
</body>

<script>
    let checkBoxes = document.querySelectorAll('input[type="checkbox"]');
    let favoriteList = [];
    <c:forEach var="i" items="${movieFavorites}">
        favoriteList.push("${i}");
    </c:forEach>

    favoriteList.forEach((item) => {
        document.getElementById('checkbox_' + item).checked = true;
    })

    $('#btn-apply').click(function (){
        //let checkBoxes = document.querySelectorAll('input[type="checkbox"]');
        let checked = "";
        checkBoxes.forEach((item) => {
            if (item.checked) {
                checked += (item.id.split('_')[1]) + " ";
            }
        })
        $.ajax({
            url: "${pageContext.request.contextPath}/alarm/movie/ajax/movieListUpdate/",
            type: "GET",
            async: false,
            data: {
                "movieIds": checked
            },
            success: function (data) {
                console.log(data);
            },
            error: function (e) {
                console.log(e);
            }
        })
        location.reload();
    })
</script>

<%--<c:forEach var="i" items="${movieFavorites}">--%>
<%--    <script>--%>
<%--        console.log("${i}");--%>
<%--    </script>--%>
<%--</c:forEach>--%>

</html>






