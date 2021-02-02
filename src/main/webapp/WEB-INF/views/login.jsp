<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>login</title>

    <%-- common_lib.jsp 의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여넣기 --%>
	<%@ include file="./common/common_lib.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	
    <!-- Custom styles for this template -->
    <link href="${cp}/css/signin.css" rel="stylesheet">
    
	<script>
		function getCookieValue(cookieStr, cookieName){
			var arr = document.cookie.split("; ");
			
			for(var i = 0; i < arr.length; i++){
				var str = arr[i].split("=");
				if(str[0] == cookieName){
					return  str[1];
				}
			}
			return "";
		}
		
		//cookieName : 추가하고자 하는 쿠키 이름
		//cookieValue : 쿠키의 값
		//expires : 유효기간(일수)
		function addCookie(cookieName, cookieValue, expires){
			var dt = new Date(); //지금 현재 날짜 ==> expires만큼 미래날짜로 변경
			dt.setDate(dt.getDate() + parseInt(expires));
			document.cookie = cookieName + "=" + cookieValue + "; " + "path=/; expires=" + dt.toGMTString();  
		}
		
		function deleteCookie(cookieName){
			addCookie(cookieName,"",-1);
		}
	
		//html 문서 로딩이 완료 되고 나서 실행 되는 코드
		$(function(){
			<c:if test="${msg != null}">
				alert("${msg}" + "ra");
			</c:if>
			
			//rememberme, userid 쿠키를 확인해서 존재할 경우 값설정, 체크
			$("#userid").val(Cookies.get("userid"))	
			if(Cookies.get("rememberme") == "Y"){
				$("#rememberme").attr("checked", true);
			}
				
			//signin 아이디를 select
			$("#signin").on("click", function(){
				//rememberme 체크박스가 체크 되어 있는지 확인
				//체크 되어 있을 경우
				if($('#rememberme').is(":checked")){
					//userid input에 있는 값을 userid쿠키로 저장
					Cookies.set("userid",$("#userid").val());
					//rememberme 쿠키로 Y값을 저장
					Cookies.set("rememberme","Y");
										
				}else{
				//체크 해제되어 있는 경우 : 더이상 사용하지 않는다는 의미미으로 userid, rememberme쿠키 삭제
					Cookies.remove("userid");
					Cookies.remove("rememberme");
				}
				//form태그를 이용하여 signin 요청
				$("#frm").submit();
			});
		});
	</script>
  </head>

  <body>
    <div class="container">
      <form id="frm" class="form-signin" action="${cp}/login/process" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userid" class="sr-only" >userid</label>
        <input type="text" name="userid" id="userid" class="form-control" placeholder="UserId"  required value="sally" autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="pass" class="form-control" placeholder="Password" required value="sallyPass">
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberme" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
      </form>
    </div> <!-- /container -->
  </body>
</html>

