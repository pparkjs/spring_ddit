<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">자유게시판 상세보기</h3>
		</div>
	</div>

	<div class="container">
		<form name="newUpdate" id="nForm" action="/free/update.do" class="form-horizontal" method="get">
			<input type="hidden" name="boNo" value="${free.boNo }">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<h3 class="card-title">${free.boTitle }</h3>
					<div class="card-tools">${freed.boWriter } ${free.boDate } ${free.boHit }</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					<div class="card-body">${free.boContent }</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<p>
						<button type="button" class="btn btn-info" id="updateBtn">수정</button>
						<button type="button" class="btn btn-danger" id="delBtn">삭제</button>
						<a href="/free/list.do" class="btn btn-primary"> 목록</a>
					</p>
				</div>
			</div>
		</form>
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
</body>
<script>
$(function(){
	var updateBtn = $("#updateBtn");
	var delBtn = $("#delBtn");
	var nForm = $("#nForm")
	
	updateBtn.on("click", function(){
		// 수정처리(페이지로 이동합니다.)
		nForm.submit();
	});
	
	delBtn.on("click", function(){
		// 삭제 처리
		if(confirm("정말 삭제하시겠습니까?")){
			// 삭제처리
			nForm.attr("method", "get");
			nForm.attr("action", "/free/delete.do");
			nForm.submit();
		}else{
			// 삭제 취소
			nForm.reset();
		}
	});
	
	if(${empty free}){
		alert("삭제된 게시글 입니다.")
		location.href = "/free/list.do"
	}
})
</script>
</html>


