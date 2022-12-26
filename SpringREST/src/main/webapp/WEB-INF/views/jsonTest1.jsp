<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>jsonTest1.jsp</h1>
	
	<input type="button" value="정보전송" id="btn1">
	
	<input type="text" name="id" id="id" >
 
<script src="https://code.jquery.com/jquery-3.6.3.js"
		integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
		crossorigin="anonymous"></script>
<script type="text/javascript">
  $(document).ready(function(){
	  
	  $("#btn1").click(function(){
		  //alert(" 클릭! ");
		  var member
		  	= {"id":$("#id").val(),"pw":"1234","no":22,"tel":"010-2222-4333"};
		  
		  // JSON.stringify(객체) : 객체정보를 JSON 형태로 변경 전달
		  
		  // 컨트롤러로 내가 원하는 정보를 전달해보자.
		  $.ajax({
			  url:"/restPost",
			  type:"post",
			  contentType:"application/json",
			  data: JSON.stringify(member),
			  success:function(){
				  alert(" 성공! ");
			  }
		  });		  		  		  
	  });	  
  });
</script>		





</body>
</html>