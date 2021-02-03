<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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



<form id="frm" action="/user/userDetailTiles">
	<input type="hidden" id="userid" name="userid" value=""/>
</form>

	<div class="row">
		<div class="col-sm-8 blog-main">
			<h2 class="sub-header">사용자(타일즈)</h2>
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
			<a class="btn btn-default pull-right" href="/user/userRegistTiles">사용자 등록</a>
			<a class="btn btn-default pull-right" href="/user/excleDownload">엑셀 다운로드</a>
			<div class="text-center">
				<ul class="pagination">
				<li><a href="${cp}/user/pagingUserTiles?page=1&pageSize=${pageVo.getPageSize()}"><span>«</span></a></li>
						<c:set var="cnt" value="0"></c:set>
						<c:choose>
							<c:when test="${pageVo.getPage() != 1 && (pageVo.getPage()+3) <= 5 }">
								<li><a href="${cp}/user/pagingUserTiles?page=1&pageSize=${pageVo.getPageSize()}"><span>1</span></a></li>
							</c:when>
							<c:when test="${pageVo.getPage() != 1 && (pageVo.getPage()+3) > 5 }">
								<li><a href="${cp}/user/pagingUserTiles?page=1&pageSize=${pageVo.getPageSize()}"><span>1</span></a></li>
								<li class="prev disabled"><span>---</span></li>
							</c:when>
						</c:choose>
						<c:forEach var="i" begin="${pageVo.getPage()}" end="${pagination}">
							<c:choose>
								<c:when test="${pageVo.getPage() == i && i > 2}">
									<c:set var="cnt" value="2"></c:set>
									<li><a href="${cp}/user/pagingUserTiles?page=${i-2 }&pageSize=${pageVo.getPageSize()}"><span>${i-2 }</span></a></li>
									<li><a href="${cp}/user/pagingUserTiles?page=${i-1 }&pageSize=${pageVo.getPageSize()}"><span>${i-1 }</span></a></li>
									<li class="active"><span>${i }</span></li>	
								</c:when>
								<c:when test="${pageVo.getPage() == i && i <= 2}">
									<li class="active"><span>${i }</span></li>	
								</c:when>
								<c:otherwise>
									<c:if test="${cnt <=5 }">
										<c:set var="cnt" value="${cnt+1}"></c:set>
										<li><a href="${cp}/user/pagingUserTiles?page=${i }&pageSize=${pageVo.getPageSize()}"><span>${i }</span></a></li>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pageVo.getPage()+2 ==  pagination}">
								<li class="next"><a href="${cp}/user/pagingUserTiles?page=${pagination}&pageSize=${pageVo.getPageSize()}">»</a></li>
							</c:when>
							<c:otherwise>
								<li class="prev disabled"><span>---</span></li>
								<li><a href="${cp}/user/pagingUserTiles?page=${pagination}&pageSize=${pageVo.getPageSize()}"><span>${pagination}</span></a></li>
								<li class="next"><a href="${cp}/user/pagingUserTiles?page=${pagination}&pageSize=${pageVo.getPageSize()}">»</a></li>
							</c:otherwise>
						</c:choose>
				</ul>
			</div>
		</div>
	</div>

