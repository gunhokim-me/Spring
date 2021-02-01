<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>allUser</title>

<%@ include file="../common/common_lib.jsp"%>
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">

<script>
	//문서가 로딩이 완료되고 나서 실행되는 영역
	$(function(){
		$(".user").on("click", function(){
			//this : 클릭 이벤트가 발생한 element
			//data-속성명  data-userid, 속성명은 대소문자를 가리지 않고 소문자로 인식
			var userid = $(this).data("userid")
			$("#userid").val(userid);
			console.log(1)
			$("#frm").submit();
		});
	});
</script>
</head>

<body>
<form id="frm" action="${cp}/user">
	<input type="hidden" id="userid" name="userid" value=""/>
</form>

	<!-- 헤더 추가 -->
	<%@include file="../common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
			<!-- 레프트 추가 -->
				<%@ include file="../common/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								<c:forEach items="${userList}" var="user">
									<tr class="user" data-userid="${user.userid }">
										<td>${user.userid }</td>
										<td>${user.usernm }</td>
										<td>${user.alias }</td>
										<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<a class="btn btn-default pull-right" href="/user/registUser.jsp">사용자 등록</a>
						<div class="text-center">
							<ul class="pagination">
							<li><a href="${cp}/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><span>«</span></a></li>
									<c:set var="cnt" value="0"></c:set>
									<c:choose>
										<c:when test="${pageVo.getPage() != 1 && (pageVo.getPage()+3) <= 5 }">
											<li><a href="${cp}/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><span>1</span></a></li>
										</c:when>
										<c:when test="${pageVo.getPage() != 1 && (pageVo.getPage()+3) > 5 }">
											<li><a href="${cp}/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><span>1</span></a></li>
											<li class="prev disabled"><span>---</span></li>
										</c:when>
									</c:choose>
									<c:forEach var="i" begin="${pageVo.getPage()}" end="${pagination}">
										<c:choose>
											<c:when test="${pageVo.getPage() == i && i > 2}">
												<c:set var="cnt" value="2"></c:set>
												<li><a href="${cp}/pagingUser?page=${i-2 }&pageSize=${pageVo.getPageSize()}"><span>${i-2 }</span></a></li>
												<li><a href="${cp}/pagingUser?page=${i-1 }&pageSize=${pageVo.getPageSize()}"><span>${i-1 }</span></a></li>
												<li class="active"><span>${i }</span></li>	
											</c:when>
											<c:when test="${pageVo.getPage() == i && i <= 2}">
												<li class="active"><span>${i }</span></li>	
											</c:when>
											<c:otherwise>
												<c:if test="${cnt <=5 }">
													<c:set var="cnt" value="${cnt+1}"></c:set>
													<li><a href="${cp}/pagingUser?page=${i }&pageSize=${pageVo.getPageSize()}"><span>${i }</span></a></li>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${pageVo.getPage()+2 ==  pagination}">
											<li class="next"><a href="${cp}/pagingUser?page=${pagination}&pageSize=${pageVo.getPageSize()}">»</a></li>
										</c:when>
										<c:otherwise>
											<li class="prev disabled"><span>---</span></li>
											<li><a href="${cp}/pagingUser?page=${pagination}&pageSize=${pageVo.getPageSize()}"><span>${pagination}</span></a></li>
											<li class="next"><a href="${cp}/pagingUser?page=${pagination}&pageSize=${pageVo.getPageSize()}">»</a></li>
										</c:otherwise>
									</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
