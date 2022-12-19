<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

     <h1>/board/regist.jsp</h1>

	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"> 게시판 글쓰기 </h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form role="form" action="" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="aaa">제   목</label> <input
					type="text" class="form-control" name="title" id="aaa" placeholder="제목을 입력하세요.">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">작 성 자</label> <input
					type="text" class="form-control" name="writer" id="exampleInputPassword1" placeholder="작성자를 입력하세요.">
			</div>
			<div class="form-group">
				<label>Textarea</label>
				<textarea class="form-control" name="content" rows="3" placeholder="내용을 입력하세요."></textarea>
			</div>
		</div>
		<!-- /.box-header -->
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</form>
</div>


<%@ include file="../include/footer.jsp" %>
