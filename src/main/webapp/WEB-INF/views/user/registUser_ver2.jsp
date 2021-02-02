<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">
<%
	UserVo vo2 = (UserVo) request.getAttribute("vo");
%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	status = false

	//주소검색 버튼이 클릭 되었을 때 다음 주소 api팝업을 연다
		$("#test").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {
					$("#zipcode").val(data.zonecode);
					$("#addr1").val(data.address);

					//사용자 편의성을 위해 상세주소 입력 input 태그로 focus
					$("#addr2").focus();
				}
			}).open();
		});
		$('#idcheck').on("click", function() {
			id = $("#userid").val();
			if (id.length == 0 || id == null)
				return alert("아이디를 입력하세요.")

			$.ajax({
				type : "get",
				url : "/registUser",
				dataType : "json",
				data : {
					id : id
				},
				success : function(data) {
					if (data.res == 0) {
						alert("사용할 수 있는 ID입니다.");
						status = true;
						$("#idcheck").prop('disabled', true);
					} else {
						alert("중복된 아이디입니다.");
					}
				},
				error : function(xhr) {
					alert(xhr.status())
				}
			})
		});
		$("#userid").keyup(function(){
			$("#idcheck").prop('disabled', false);
			status = false;
		});
		$("#regist").on("click", function(){
			if(status == false){
				alert("아이디 중복검사를 실행해 주세요.")
			}else{
				console.log("why")
				$("#test1").submit();
			}
		})
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
			
				<form class="form-horizontal" id ="test1"role="form" action="${cp }/registUser" method="post">
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="userid" name="userid" placeholder="사용자 아이디" value="${param.userid }">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default" id="idcheck">중복검사</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" value="${param.usernm }" id="usernm" name="usernm" placeholder="사용자 이름">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control"value="${param.pass}" id="pass" name="pass" placeholder="비밀번호">
						</div>
					</div>
					
	<!-- 				<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="regdt" name="regdt" placeholder="등록일시">
						</div>
					</div> -->

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"value="${param.alias }" id="alias" name="alias" placeholder="별명">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  value="${param.addr1 }" id="addr1" name="addr1" placeholder="도로주소" readonly="readonly">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default" id="test">주소검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" value="${param.addr2}" id="addr2" name="addr2" placeholder="상세주소">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호 코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" value="${param.zipcode}" placeholder="우편번호 코드" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" id="regist" class="btn btn-default">사용자 등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>