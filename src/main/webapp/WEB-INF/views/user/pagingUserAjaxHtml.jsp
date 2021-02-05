<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${userList}" var="user">
	<tr class="user" data-userid="${user.userid }">
		<td>${user.userid }</td>
		<td>${user.usernm }</td>
		<td>${user.alias }</td>
		<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
	</tr>
</c:forEach>
####################
<li>
	<a href="javascript:pagingUserAjax(1,${pageVo.getPageSize()})"><span>«</span></a></li>
<c:set var="cnt" value="0"></c:set>
<c:choose>
	<c:when test="${pageVo.getPage() != 1 && (pageVo.getPage()+3) <= 5 }">
		<li><a href="javascript:pagingUserAjax(1,${pageVo.getPageSize()})"><span>1</span></a></li>
	</c:when>
	<c:when test="${pageVo.getPage() != 1 && (pageVo.getPage()+3) > 5 }">
		<li><a href="javascript:pagingUserAjax(1,${pageVo.getPageSize()})"><span>1</span></a></li>
		<li class="prev disabled"><span>---</span></li>
	</c:when>
</c:choose>
<c:forEach var="i" begin="${pageVo.getPage()}" end="${pagination}">
	<c:choose>
		<c:when test="${pageVo.getPage() == i && i > 2}">
			<c:set var="cnt" value="2"></c:set>
			<li><a href="javascript:pagingUserAjax(${i-2 },${pageVo.getPageSize()})"><span>${i-2 }</span></a></li>
			<li><a href="javascript:pagingUserAjax(${i-1 },${pageVo.getPageSize()})"><span>${i-1 }</span></a></li>
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:when test="${pageVo.getPage() == i && i <= 2}">
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:otherwise>
			<c:if test="${cnt <=5 }">
				<c:set var="cnt" value="${cnt+1}"></c:set>
				<li><a href="javascript:pagingUserAjax(${i },${pageVo.getPageSize()})"><span>${i }</span></a></li>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${pageVo.getPage()+2 ==  pagination}">
		<li class="next"><a href="javascript:pagingUserAjax(${pagination },${pageVo.getPageSize()})">»</a></li>
	</c:when>
	<c:otherwise>
		<li class="prev disabled"><span>---</span></li>
		<li><a href="javascript:pagingUserAjax(${pagination },${pageVo.getPageSize()})"><span>${pagination}</span></a></li>
		<li class="next"><a href="javascript:pagingUserAjax(${pagination },${pageVo.getPageSize()})">»</a></li>
	</c:otherwise>
</c:choose>
