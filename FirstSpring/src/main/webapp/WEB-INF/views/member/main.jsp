<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<h1>/member/main.jsp</h1>
	
	${sessionScope.id } 님 환영합니다. <br>
	${id } 님 환영합니다. <br>
	
	<input type="button" value="로그아웃" onclick="location.href='/member/logout';">
</body>
</html>