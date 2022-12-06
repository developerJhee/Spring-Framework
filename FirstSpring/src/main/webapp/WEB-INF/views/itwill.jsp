<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>itwill.jsp</h1>
	
	<h3>msg값</h3>
	
	JSP(param) : <%=request.getParameter("msg")%> <hr>
	JSP(attribute) : <%=request.getAttribute("msg")%> <hr>
	
	JSP(param) : <%=request.getParameter("tel")%> <hr>
	JSP(attribute) : <%=request.getAttribute("tel")%> <hr>
	
	EL : ${msg } <hr> <!-- ﻿@ModelAttribute("파라미터명") String 변수에 의해 받아올 수 있다. -->	
	<!-- 위처럼 이렇게 쓰면 안된다 주소줄 영역에 담아왔기 때문에 param.을 접두에 꼭 붙여줘야한다. -->
	EL(r) : ${requestScope.msg } <hr>
	EL(s) : ${sessionScope.msg } <hr>
	EL(p) : ${pageScope.msg } <hr>
	EL(a) : ${applicationScope.msg } <hr>

	EL : ${param.msg } <hr>
	
	<hr>
	
	<h3>tel값</h3>
	
	JSP(param) : <%=request.getParameter("tel")%> <hr>
	JSP(attribute) : <%=request.getAttribute("tel")%> <hr>
	
	EL(tel) : ${tel } <hr> <!-- ﻿@ModelAttribute("파라미터명") String 변수에 의해 받아올 수 있다. -->	
	<!-- 위처럼 이렇게 쓰면 안된다 주소줄 영역에 담아왔기 때문에 param.을 접두에 꼭 붙여줘야한다. -->
	EL(r) : ${requestScope.tel } <hr>
	EL(s) : ${sessionScope.tel } <hr>
	EL(p) : ${pageScope.tel } <hr>
	EL(a) : ${applicationScope.tel } <hr>

	EL(param.tel) : ${param.tel } <hr>
	

</body>
</html>