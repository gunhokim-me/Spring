<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
	UserVo vo2 = (UserVo) request.getAttribute("vo");
%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	status = false
	<%if (vo2 != null) {%>
		$("#userid").val("<%=vo2.getUserid()%>");
		$("#usernm").val("<%=vo2.getUsernm()%>");
		$("#pass").val("<%=vo2.getPass()%>");
		$("#alias").val("<%=vo2.getAlias()%>");
		$("#addr1").val("<%=vo2.getAddr1()%>");
		$("#addr2").val("<%=vo2.getAddr2()%>");
		$("#zipcode").val("<%=vo2.getZipcode()%>");
<%}%>
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

	<form class="form-horizontal" id ="test1"role="form" action="/user/userRegistsubmit" method="post" enctype="multipart/form-data">
					spring message : <spring:message code="GREETING" arguments="brown"/>
					spring message 적용
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERID"/></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="userid" name="userid" placeholder="사용자 아이디">
							
							<!-- 어떤 필드에 어떤 에러가 있는지 확인해줌(길이든 뭐든 모든 에러) -->
							<span style="color:red;"><form:errors path="userVo.userid"/></span>
						<input type="file" class="fom-control" name="profile"/>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default" id="idcheck">중복검사</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERNM"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm" placeholder="사용자 이름">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label"><spring:message code="PASS"/></label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호">
						</div>
					</div>
					
	<!-- 				<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="regdt" name="regdt" placeholder="등록일시">
						</div>
					</div> -->

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ALIAS"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" placeholder="별명">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR1"/></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1" placeholder="도로주소" readonly="readonly">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default" id="test">주소검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR2"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ZIPCODE"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호 코드" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" id="regist" class="btn btn-default">사용자 등록</button>
						</div>
					</div>
				</form>
				<select name="lang">
					<option value="">언어설정</option>
					<option value="ko">한국어</option>
					<option value="en">English</option>
				</select>
<script>
$(function(){
	$("select[name=lang]").on("change",function(){
		document.location = "/user/userRegistTiles?lang=" + $(this).val();
	});
	$("select[name=lang]").val("${param.lang}");
});
</script>
