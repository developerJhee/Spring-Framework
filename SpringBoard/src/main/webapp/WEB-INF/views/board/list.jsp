<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<h1> /board/list.jsp </h1>
<%-- 		 ${boardList } --%>
			${result}
<%-- 	request : ${requestScope.result } --%>
<%--  	param : ${param.result } --%>
<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">Bordered Table</h3>
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 50px">번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th style="width: 60px">조회수</th>
				</tr>
				
				<c:forEach var="vo" items="${boardList }">
				<tr>
					<td>${vo.bno }</td>
					<td>
						<a href="/board/read?bno=${vo.bno }"> ${vo.title } </a>
					</td>
					<td>${vo.writer }</td>
					<td>
						<fmt:formatDate value="${vo.regdate }" pattern="yy-MM-dd"/>
					</td>
					<td><span class="badge bg-red">${vo.viewcnt }</span></td>
				</tr>
				</c:forEach>				
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			<li><a href="#">«</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">»</a></li>
		</ul>
	</div>
</div>

	<script type="text/javascript">
		// alert(${result}); // 이렇게 보내면 변수로 인식돼서 변수가 없어서 불러올 수 없다고 오류뜸
		// alert('${result}'); // 문자열로 인식하게 하니 작동이 된다.
		// EL 표현식 -> JS사용 값전달 가능 (DB데이터도 사용가능)
		// ajax호출 시 (db데이터 전달 가능)
		var result = '${result}';
		if(result == 'createOK'){
			alert("글쓰기 완료!");
		}
		
		if(result == 'modOK'){
			alert("글 수정 완료!");
		}
		
		if(result == 'delOK'){
			alert("글 삭제 완료!");
		}
		
	</script>

<%@ include file="../include/footer.jsp" %>