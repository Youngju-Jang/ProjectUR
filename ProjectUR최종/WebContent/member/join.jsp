<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>uR</title>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<!-- 헤더 -->
    <%@include file="../inc/header.jsp" %>
<%if(id!=null){%>
	<script type="text/javascript">
	alert('로그아웃 후 이용해주세요');
	history.back();
	window.location = document.referrer;
	</script>
<% }%>
    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="#"><i class="fa fa-home"></i> Home</a>
                        <span>회원가입</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Form Section Begin -->

    <!-- Register Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <div class="register-form">
                        <h2>회원가입</h2>
                        
<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

//우편번호 api
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('address2').focus();
        }
    }).open();
}


function sendEmail() {
	var width = 430;
	var height = 200;
	var winL = (screen.width - width) / 2;
	var winT = (screen.height - height) / 2;
	var property = "width=" + width + "," + "height=" + height + "," 
					+ "left=" + winL + "," + "top=" + winT + " menubar=no";
	
	window.open("mail.me?to=" + email.value, "인증 페이지", property);
}


$(function(){ 
	$("#alert-success").hide(); 
	$("#alert-danger").hide(); 
	$("input").keyup(function(){ 
		var pass=$("#pass").val(); 
		var pass2=$("#pass2").val(); 
		if(pass != "" || pass2 != ""){ 
			if(pass == pass2){ 
				$("#alert-success").show(); 
				$("#alert-danger").hide(); 
				}else{ 
					$("#alert-success").hide(); 
					$("#alert-danger").show(); 
				} 
			} 
		}); 
	});
	

</script>                      

                               
                        
                        <form action="./MemberJoinAction.me" method="post">
                            <div class="group-input">
                                <label for="username">아이디 *</label>
                                <input type="text" id="username" name="id" required="required">
                            </div>
                            <div class="group-input">
                                <label for="pass">비밀번호 *</label>
                                <input type="password" id="pass" name="pass" required="required">
                            </div>
                            <div class="group-input">
                                <label for="pass2">비밀번호 확인 *</label>
                                <input type="password" id="pass2" name="pass2" required="required">
                                <div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div> 
                                <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>

                            </div>
                            <div class="group-input">
                                <label for="email">이메일 *</label>
                                <input type="email" id="email" name="email" required="required">
                                <button class="site-btn post" onclick="sendEmail();">인증요청</button>      
                                
                            </div>                            
                            <div class="group-input">
                                <label for="address">주소 *</label>
                                <input type="text" id="postcode" name="postcode" placeholder="우편번호" readonly="readonly" required="required">
                                <input type="button" class="site-btn post" onclick="execDaumPostcode()" value="주소검색">
                                <input type="text" id="address1" name="address1" placeholder="주소" readonly="readonly" required="required">
                                <input type="text" id="address2" name="address2" placeholder="상세 주소" required="required">
                            </div>                            
                            <button type="submit" class="site-btn register-btn">회원가입</button>
                        </form>
                        <div class="switch-login">
                            <a href="./Login.me" class="or-login">Or Login</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Form Section End -->
    
    <!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>
</body>
</html>