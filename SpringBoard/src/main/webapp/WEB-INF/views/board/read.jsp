<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@ include file="../include/header.jsp" %>

     <h1>/board/read.jsp</h1>
	
	<!-- 수정, 삭제, bno정보를 전달하는 폼태그 -->
	<form role="form" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
	</form>
	
<%--     ${vo } --%>
	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"> 게시판 본문내용 </h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
		<!--  <form role="form" action="" method="post"> -->
              <div class="box-body">
                <div class="form-group">
                  <label for="aaa">글 번 호</label>
                  <input type="text" name="title" class="form-control" id="aaa" value="${vo.bno }" readonly>
                </div>
                
                <div class="form-group">
                  <label for="aaa">제  목</label>
                  <input type="text" name="title" class="form-control" id="aaa" value="${vo.title }" readonly>
                </div>
                
                <div class="form-group">
                  <label for="exampleInputPassword1">작 성 자</label>
                  <input type="text" name="writer" class="form-control" id="exampleInputPassword1" value="${vo.writer }" readonly>
                </div>
                
                <div class="form-group">
                  <label>내   용</label>
                  <textarea name="content" class="form-control" rows="3" placeholder="내용을 입력하세요." readonly>${vo.content }</textarea>
                </div>

              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-danger">수정</button>
                <button type="submit" class="btn btn-warning">삭제</button>
                <button type="submit" class="btn bg-purple">목록</button>
              </div>
		<!-- </form> -->
          </div>
          
          <!-- jQuery 사용한 페이지 이동 -->
<!-- jQuery 2.1.4 -->
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>  
<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']"); // 폼 안에 bno를 전달하기위해 폼을 변수지정
		// console.log(formObj); => 개발자 도구에서 form 태그가 잘 전달 됐는지 확인할 수 있다.
		 
		$(".btn-danger").click(function(){
			// 수정버튼 (bno 가지고 submit-/board/modify)
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "get");
			formObj.submit(); // form태그를 submit방식으로 보내자.
		});
		
		
		// 삭제버튼(bno 가지고 submit-/board/remove)
		$(".btn-warning").click(function(){
			// 바로 삭제동작 -> POST
			formObj.attr("action", "/board/remove");
			// formObj.attr("method", "post"); => 기본값이 post라서 굳이 안 적어도 된다.
			formObj.submit(); // form태그를 submit방식으로 보내자.
		});
		
		
		$(".bg-purple").click(function(){
			// 목록으로 이동
			location.href="/board/list";
		});
	});
</script>  
          
          
          
   <%@ include file="../include/footer.jsp" %>