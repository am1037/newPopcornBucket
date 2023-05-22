<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<%--    <script src="${pageContext}/resources/js/jquery-3.6.4.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="container">
    <div style="text-align: center">
        <details>
            <summary>복붙의 수고를 덜기 위한</summary>
            <p>0004,0009,0056,0257<br>
            </p>빈 필하모닉 정기연주회 - 안드리스 넬슨스,뮤지컬 공연실황, 알쏭달쏭 캐치! 티니핑 &lt;신비한 상자를 열어라!>,슬픔의 삼각형,빈 필하모닉 정기연주회 - 크리스티안 틸레만,2001 스페이스 오디세이,존 윅 4,날씨의 아이,클로즈,더 퍼스트 슬램덩크,극장판 짱구는 못말려-동물소환 닌자 배꼽수비대,유랑지구2,스즈메의 문단속,너의 이름은.,칠중주-홍콩 이야기,토리와 로키타,드림,문재인입니다,롱디,슈퍼 마리오 브라더스,분노의 질주-라이드 오어 다이,가디언즈 오브 갤럭시-Volume 3,
        </details>
        <br>
        <div class="mb-3">
            <label for="t-favs" class="form-label">극장 즐겨찾기 입력란입니당(띄어쓰기하지마세요)</label>
            <input type="text" class="form-control" id="t-favs" placeholder="ex)0004,0009,0056,0257">
        </div>
        <div class="mb-3">
            <label for="m-favs" class="form-label">영화 즐겨찾기 입력란입니당(영화명을정확하게입력해야합니당)</label>
            <input type="text" class="form-control" id="m-favs" placeholder="ex)가디언즈 오브 갤럭시-Volume 3,존 윅 4">
        </div>
        <input id="threshold" placeholder="threshold"><button id="btn">버튼이랍니당</button>

        <div id="result">

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script>
    $('#btn').click(function () {
        $.ajax({
            url: '111',
            type: 'GET',
            data: {
                tfavs: $('#t-favs').val(),
                mfavs: $('#m-favs').val(),
                threshold: $('#threshold').val(),
            },
            success: function (data) {
                console.log(data);
                $('#result').html(data);
            }
        });
    });
</script>

</body>
</html>