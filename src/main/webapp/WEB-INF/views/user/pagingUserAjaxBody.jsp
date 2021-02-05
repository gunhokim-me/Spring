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
		pagingUserAjax(1,5);
	
		$("#userTbody").on("click",".user", function(){
			//this : 클릭 이벤트가 발생한 element
			//data-속성명  data-userid, 속성명은 대소문자를 가리지 않고 소문자로 인식
			var userid = $(this).data("userid")
			$("#userid").val(userid);
			console.log(1)
			$("#frm").submit();
		});
	});
	
	function pagingUserAjax(page, pageSize){
		//ajax를 통해 사용자 리스트를 가져온다 : 1page, 5pageSize
		$.ajax({
//			url : "/user/pagingUserAjax",
			url : "/user/pagingUserAjaxHtml",
			data : "page="+ page +"&pageSize="+pageSize,
			dataType : "html",
			success : function(data){
				var html = data.split("####################");
				$("#userTbody").html(html[0]);
				$("#pagination").html(html[1]);
			}
		});
	}
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
					<tbody id="userTbody"></tbody>
				</table>
			</div>
			<a class="btn btn-default pull-right" href="/user/userRegistTiles">사용자 등록</a>
			<a class="btn btn-default pull-right" href="/user/excleDownload">엑셀 다운로드</a>
			<div class="text-center">
				<ul class="pagination" id="pagination">
				
				</ul>
			</div>
		</div>
	</div>

