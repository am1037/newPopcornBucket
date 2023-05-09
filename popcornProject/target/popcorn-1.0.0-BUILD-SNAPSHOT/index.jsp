<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P><br>
<form action="go">
	<button name="name" value="kjm" type="submit">김준명</button>
	<button name="name" value="nyk" type="submit">나유경</button>
	<button name="name" value="nji" type="submit">노지인</button>
	<button name="name" value="pkw" type="submit">박근우</button>
	<button name="name" value="lsh" type="submit">이시현</button>
	<button name="name" value="jmk" type="submit">정민기</button>
</form>
</body>
</html>
