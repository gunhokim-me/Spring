<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>Jsp</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	//주소검색 버튼이 클릭 되었을 때 다음 주소 api팝업을 연다
	$("#test").on("click",function(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	            $("#zipcode").val(data.zonecode);
	            $("#addr1").val(data.address);
	            
	            //사용자 편의성을 위해 상세주소 입력 input 태그로 focus
	            $("#addr2").focus();
	        }
	    }).open();
	});
});
</script>

</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/views/common/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form class="form-horizontal" role="form" action="/user/userModifySubmit" method="post" enctype="multipart/form-data">
					<input type="hidden" name = "userid" value="${uservo.userid}"/>
					<input type="hidden" name="filename" value="${uservo.filename}"/>
					<input type="hidden" name="realfilename" value="${uservo.realfilename}"/>
					<!-- 사용자 사진 출력 -->
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<img src="${cp }/profile/${uservo.userid }.png"/>
							<input type="file" class="form-control" id="profile" name="profile" value=${uservo.filename }/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label class="control-label">${uservo.userid }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm" value = "${uservo.usernm }" placeholder="사용자 이름">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass" value = "${uservo.pass }" placeholder="비밀번호">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="reg_dt" name="reg_dt" value = "<fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/>" placeholder="등록일시">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" value = "${uservo.alias }" placeholder="별명">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1" value = "${uservo.addr1 }" placeholder="도로주소" readonly="readonly">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default" id="test">주소검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" value = "${uservo.addr2 }" placeholder="상세주소">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호 코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" value = "${uservo.zipcode }" placeholder="우편번호 코드" readonly="readonly">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>