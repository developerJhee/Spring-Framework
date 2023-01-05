<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>accessErr.jsp</h1>
	<h3>${msg }</h3>
	<h3>5초뒤에 페이지 이동합니다!</h3>
	
	<%
		// response.addHeader("Refresh", "5; url=/sec/all");
	%>
</body>
</html>