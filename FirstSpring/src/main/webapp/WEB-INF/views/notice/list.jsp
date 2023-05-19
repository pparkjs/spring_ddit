<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>공지게시판 목록</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">공지게시판 목록</h1>
		</div>
	</div>
	<div class="container">
		<form action="" method="post" id="searchForm">
			<div>
				<div class="text-left">
		            <span>전체 ${pagingVO.totalRecord }건  </span>
		         </div>
			<c:set value="${sessionScope.nSearchWord }" var="searchType"/>
			<div style="padding-top: 10px">
				<div class="row" style="padding-bottom: 20px">
					<div class="col-md-7"></div>
					<div class="col-md-5">
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-10">
								<select name="searchType">
									<option value="title" <c:if test="${searchType == 'title' }"><c:out value="selected"/></c:if>>제목</option>
									<option value="writer" <c:if test="${searchType == 'writer' }"><c:out value="selected"/></c:if>>작성자</option>
									<option value="total" <c:if test="${searchType == 'total' }"><c:out value="selected"/></c:if>>제목+작성자</option>
								</select>
								<input type="text" name="searchWord" value="${searchWord }">
								<input type="submit" value="검색">
							</div>
						</div>
					</div>
				</div>
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
					<c:set value="${pagingVO.dataList }" var="noticeList"/>
					<c:choose>
						<c:when test="${empty noticeList }">
							<tr>
								<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${noticeList }" var="notice">
								<tr class="viewBtn" id="${notice.boNo }">
									<td>${notice.boNo }</td>
									<td>${notice.boTitle }</td>
									<td>${notice.boDate }</td>
									<td>${notice.boWriter }</td>
									<td>${notice.boHit }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			</div>
			<div align="left">
				<a href="/notice/form.do" onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
			</div>
			<input type="hidden" name="page" id="page" />
			<div class="card-footer clearfix" id="pagingArea">
				${pagingVO.pagingHTML}
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
	var newBtn = $("#newBtn");
	var searchForm = $("#searchForm");
	var pagingArea = $("#pagingArea");
	
	pagingArea.on("click","a", function(event){
		event.preventDefault();
		var pageNo = $(this).data("page");
		searchForm.find("#page").val(pageNo);
		searchForm.submit();
	});
	
	$(".viewBtn").on("click", function(){
		var boNo = $(this).attr("id")
		location.href = "/notice/detail.do?boNo=" + boNo;
	})
})
</script>
</html>





