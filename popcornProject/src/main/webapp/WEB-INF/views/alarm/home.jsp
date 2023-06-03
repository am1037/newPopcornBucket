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

<div id="daily">
<%--가장 상영수가 적은 영화는?--%>
</div>
<section class="main-content">
    <div class="container">
        <br>
        <br>

        <div class="row">
            <div class="col-sm-4">
                <div class="leaderboard-card">
                    <div class="leaderboard-card__body">
                        <div class="text-center">
                            <div>
                                <img src="${movie1_poster}" class="img-thumbnail img-temp" alt="User Img">
                            </div>
                            <h5 class="mb-0">${movie1_name}</h5>
                            <p class="text-muted mb-0">전국에 단 ${movie1_number}개!</p>
                            <div class="center-container d-flex justify-content-between">
                                <button>btns go here</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="leaderboard-card">
                    <div class="leaderboard-card__body">
                        <div class="text-center">
                            <div>
                                <img src="${movie2_poster}" class="img-thumbnail img-temp" alt="User Img">
                            </div>
                            <h5 class="mb-0">${movie2_name}</h5>
                            <p class="text-muted mb-0">전국에 단 ${movie2_number}개!</p>
                            <div class="center-container d-flex justify-content-between">
                                <button>btns go here</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="leaderboard-card">
                    <div class="leaderboard-card__body">
                        <div class="text-center">
                            <img src="${movie3_poster}" class="img-thumbnail img-temp" alt="User Img">
                            <h5 class="mb-0">${movie3_name}</h5>
                            <p class="text-muted mb-0">전국에 단 ${movie3_number}개!</p>
                            <div class="center-container d-flex justify-content-between">
                                <button>btns go here</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


<%--        <h4>All Users</h4>--%>

<%--        <table class="table">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>User</th>--%>
<%--                <th>Status</th>--%>
<%--                <th>Location</th>--%>
<%--                <th>Email</th>--%>
<%--                <th>Congratulate</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-center">--%>
<%--                        <img src="${pageContext.request.contextPath}/resources/img/user1.jpg" class="circle-img circle-img--small mr-2" alt="User Img">--%>
<%--                        <div class="user-info__basic">--%>
<%--                            <h5 class="mb-0">Kiran Acharya</h5>--%>
<%--                            <p class="text-muted mb-0">@kiranacharyaa</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-baseline">--%>
<%--                        <h4 class="mr-1">$1,253</h4><small class="text-success"><i class="fa fa-arrow-up"></i>5%</small>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>Bangalore</td>--%>
<%--                <td>kiran@kiranmail.com</td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-success btn-sm">Congratulate</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-center">--%>
<%--                        <img src="${pageContext.request.contextPath}/resources/img/user2.jpg" class="circle-img circle-img--small mr-2" alt="User Img">--%>
<%--                        <div class="user-info__basic">--%>
<%--                            <h5 class="mb-0">Sandeep Sandy</h5>--%>
<%--                            <p class="text-muted mb-0">@sandeep</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-baseline">--%>
<%--                        <h4 class="mr-1">$1,051</h4><small class="text-success"><i class="fa fa-arrow-up"></i>5%</small>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>Bangalore</td>--%>
<%--                <td>sandeep@sandeepmail.com</td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-success btn-sm">Congratulate</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-center">--%>
<%--                        <img src="${pageContext.request.contextPath}/resources/img/user3.jpg" class="circle-img circle-img--small mr-2" alt="User Img">--%>
<%--                        <div class="user-info__basic">--%>
<%--                            <h5 class="mb-0">John Doe</h5>--%>
<%--                            <p class="text-muted mb-0">@johndoe</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-baseline">--%>
<%--                        <h4 class="mr-1">$1,012</h4><small class="text-success"><i class="fa fa-arrow-up"></i>5%</small>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>Bangalore</td>--%>
<%--                <td>kiran@kiranmail.com</td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-success btn-sm">Congratulate</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-center">--%>
<%--                        <img src="${pageContext.request.contextPath}/resources/img/user4.jpg" class="circle-img circle-img--small mr-2" alt="User Img">--%>
<%--                        <div class="user-info__basic">--%>
<%--                            <h5 class="mb-0">John Noakes</h5>--%>
<%--                            <p class="text-muted mb-0">@johnnoakes</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-baseline">--%>
<%--                        <h4 class="mr-1">$986</h4><small class="text-success"><i class="fa fa-arrow-up"></i>5%</small>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>Bangalore</td>--%>
<%--                <td>kiran@kiranmail.com</td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-success btn-sm">Congratulate</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-center">--%>
<%--                        <img src="${pageContext.request.contextPath}/resources/img/user5.jpg" class="circle-img circle-img--small mr-2" alt="User Img">--%>
<%--                        <div class="user-info__basic">--%>
<%--                            <h5 class="mb-0">Tom harry</h5>--%>
<%--                            <p class="text-muted mb-0">@tomharry</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <div class="d-flex align-items-baseline">--%>
<%--                        <h4 class="mr-1">$951</h4><small class="text-success"><i class="fa fa-arrow-up"></i>5%</small>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>Bangalore</td>--%>
<%--                <td>kiran@kiranmail.com</td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-success btn-sm">Congratulate</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
    </div>
</section>

</body>

</html>






