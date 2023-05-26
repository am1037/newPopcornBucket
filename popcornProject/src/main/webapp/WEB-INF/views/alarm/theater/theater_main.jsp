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
</head>

<body>
<div id="navbar-container">
    error while loading navbar
</div>

<div class="container-fluid mt-3">
    <div id="div-current-favorites">

        <div id="div-current-favorites-inner">
        </div>
    </div>

    <div id="select-div-region" class="container-fluid mt-3">
        <select id="select-region">
            <option>
                지역 선택
            </option>
            <script>
                let str_select_theater = "<select id='select-theater'>";
                let theaterCodeMap = new Map();
            </script>
            <c:forEach var="i" items="${regionSet}">
                <option value="${i}">${i}</option>
                <script>
                    <c:forEach var="j" items="${theaterMapByRegion[i]}">
                        str_select_theater = str_select_theater + "<option value='${j.theater_id}'>${j.theater_name}</option>";
                    </c:forEach>
                    theaterCodeMap.set("${i}", str_select_theater+"</select>");
                    str_select_theater = "<select id='select-theater'>";
                </script>
            </c:forEach>
        </select>

        <div id="select-div-theater">
        </div>

        <div id="tempo-div">

        </div>
        <button type='button' id="btn-insert" class='btn btn-primary'>Insert</button>
        <button type='button' id="btn-apply" class='btn btn-primary'>Apply</button>
    </div>

</div>

</body>
<script>
    let region = $('#select-region')[0];
    region.addEventListener('change', function () {
        console.log(theaterCodeMap.get(region.value));
        $('#select-div-theater').html(theaterCodeMap.get(region.value));
    });

    let key = "";
    let str_favorites = "";
    let codes_favorites = [];

    <c:forEach var="i" items="${theater_favorites}">
        value = ('0000' + ${i}).slice(-4) + " ";
        console.log("${theater_rawMap[i]}");
        str_favorites = str_favorites +
            "<div class='card' style='width:200px'>" +
            "<div class='card-header'>${theater_rawMap[i].theater_name}</div>" +
            "<div class='card-body'>${theater_rawMap[i].theater_location}</div>" +
            "<div class='card-footer'>" +
            // "<div class='container mt-3'>" +
            <%--    "<button type='button' class='btn btn-primary' id='btn_detail_${theater_rawMap[i].theater_id}'>Detail</button>" +--%>
            <%--    " " +--%>
            "<button type='button' class='btn btn-primary primary' id='btn_delete_${theater_rawMap[i].theater_id}'>Delete</button>" +
            "<script>" +
            "$('#btn_delete_${theater_rawMap[i].theater_id}').click(function () {" +
            "    let element = document.querySelector('#btn_delete_${theater_rawMap[i].theater_id}'); " +
            "    console.log(element.classList); " +
            "    if (element.classList.contains('btn-primary')){ " +
            "        element.classList.remove('btn-primary'); " +
            "        element.classList.add('btn-danger'); " +
            "        element.classList.remove('primary'); " +
            "        element.classList.add('danger'); " +
            "    } else { " +
            "        element.classList.remove('btn-danger'); " +
            "        element.classList.add('btn-primary'); " +
            "        element.classList.remove('danger'); " +
            "        element.classList.add('primary'); " +
            "    } " +
            "}); " +
            "<\/script>" +
            // "</div>" +
            "</div>" +
            "</div>";
        codes_favorites.push(value);
    </c:forEach>

    console.log(typeof str_favorites);
    $('#div-current-favorites-inner').html(str_favorites);

        let tempo_div = $('#tempo-div');
        let tempo_div_str = "";
        let request = {
            primaries : [],
            warnings : [],
            dangers : []
        }

    $('#btn-insert').click(function (){
        let str = $('#select-theater').val();
        let str2 = $('#select-theater option:selected').text();
        if (!str){
            console.log("지역을 선택해주세요");
            console.log(request.warnings);
        }else {
            tempo_div_str += str2 + " ";
            tempo_div.html(tempo_div_str);
            console.log(str + " " + str2);
            request.warnings.push(str)
        }
    })

    $('#btn-apply').click(function () {
        //let elements_primary = document.querySelectorAll('.primary');
        let elements_danger = document.querySelectorAll('.danger');
        // for (let i = 0; i < elements_primary.length; i++) {
        //     request.primaries.push(elements_primary[i].id.split('_')[2]);
        // }
        // for (let i = 0; i < elements_primary.length; i++) {
        //     request.warnings.push(elements_primary[i].id.split('_')[2]);
        // }
        for (let i = 0; i < elements_danger.length; i++) {
            request.dangers.push(elements_danger[i].id.split('_')[2]);
        }
        console.log(request);
        $.ajax({
            url: "${pageContext.request.contextPath}/alarm/theater/request",
            type: "POST",
            async: false,
            contentType: "application/json",
            data: JSON.stringify(request),
            success: function () {
                console.log("success");
            },
            error: function (e) {
                console.log(e);
            }
        });
        // 나중에 redirect로 바꾸세요!!
        location.reload();
    });
</script>
</html>



