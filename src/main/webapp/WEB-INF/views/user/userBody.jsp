<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	//문서 로딩이 완료 되었을 때
$(function(){
	$("#modifyBtn").on("click", function(){
		$("#frm").attr("method","get");		
		$("#frm").attr("action","/user/userModifyTiles");
		$("#frm").submit();
	});
	$("#deleteBtn").on("click", function(){
		$("#frm").attr("method","post");		
		$("#frm").attr("action","/user/userDeleteTiles");
		$("#frm").submit();
	});
});
</script>

	<form class="form-horizontal" role="form" id="frm" action="${cp}/userModifyTiles">
					<input type = "hidden" name="userid" value="${user.userid}"/>
					<input type="hidden" name="filename" value="${user.filename}"/>
					<input type="hidden" name="realfilename" value="${user.realfilename}"/>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<%-- <img alt="이미지 없음" src="${cp}/profile/${user.userid }.png"> --%>
							
							<a href="/user/profileDownload?userid=${user.userid }"> <!-- 이미지 클릭 시 다운로드 -->
								<img alt="이미지 어디감?" src="/user/profile?userid=${user.userid }"> <!-- 이미지를 서블릿에서 불러오는 것 -->
							</a>
							
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label class="control-label">${user.userid }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<label class="control-label">${user.usernm }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
						<label class="control-label">${user.pass }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<label class="control-label"><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<label class="control-label">${user.alias}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-10">
							<label class="control-label">${user.addr1 }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<label class="control-label">${user.addr2 }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호 코드</label>
						<div class="col-sm-10">
							<label class="control-label">${user.zipcode }</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
<!--						사용자 수정 : method - get = /userModify
							사용자 삭제 : mothod - post = /deleteUser
							파라미터는 둘다 userid 하나만 있으면 가능 -->
							<button type="button" id="modifyBtn" class="btn btn-default">사용자 수정</button>
							<button type="button" id="deleteBtn" class="btn btn-default">사용자 삭제</button>
						</div>
					</div>
				</form>
