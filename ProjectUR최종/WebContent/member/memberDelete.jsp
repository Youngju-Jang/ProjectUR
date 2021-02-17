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

    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="#"><i class="fa fa-home"></i> Home</a>
                        <span>회원탈퇴</span>
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
            	<div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
					<div class="filter-widget">
						<ul class="filter-catagories">
							<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
							<li> <a href="./MemberInfo.me">내 정보 보기</a></li>
							<li> <a href="./passCheck.me">내 정보 변경</a></li>
							<li> <a href="./MemberOrderList.me"> 주문 이력</a></li>
							<li> <a href="./MemberReviewList.me"> 나의 리뷰</a></li>
							<li> <a href="./MemberQnaList.me"> 상품 QnA</a></li>
							<li class="check-menu"> <a href="./MemberDelete.me"> 회원탈퇴</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9 order-1 order-lg-2">
					<div class="pass-check-form">
						<h2>회원탈퇴</h2>
						<%
					    String idc = (String) session.getAttribute("id");
					  	if (idc == null){
						  response.sendRedirect("./Login.me");  
					  	}  
					    %>

						<form action="./MemberDeleteAction.me" method="post"
							onsubmit="return checkForm();">
							<input type="hidden" name="id" value="<%=id%>">
							<textarea readonly rows="20" cols="30">
 이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
- '회원'이라 함은 이 약관에 동의하고 회원가입을 한 자로서, 회사가 제공하는 서비스를 이용할 수 있는 이용자를 말합니다.
- '이용자ID'라 함은 회원의 식별 및 서비스 이용을 위하여 회원의 신청에 따라 회사가 회원별로 부여하는 고유한 문자와 숫자의 조합을 말합니다.
- '비밀번호'라 함은 이용자ID로 식별되는 회원의 본인 여부를 검증하기 위하여 회원이 설정하여 회사에 등록한 고유의 문자와 숫자의 조합을 말합니다.
- '탈퇴'라 함은 회원이 서비스 이용을 해지하는 것을 말합니다.
회원은 회사에 언제든지 회원 탈퇴를 요청할 수 있으며 회사는 요청을 받은 즉시 해당 회원의 회원 탈퇴를 위한 절차를 밟아 개인정보취급방침에 따라 회원 등록을 말소합니다.
회사의 모든 서비스에서 이용중인 서비스의 기간이 남아있는 회원이 탈퇴 요청하였을 경우 회원탈퇴로 인한 서비스의 중지 또는 피해는 회원탈퇴 이용자에게 있습니다.
       						</textarea>
							<br> 
							<input type="checkbox" id="agree">네, 동의합니다 <br>
							<input type="password" name="pass" id="pass" class="delete" placeholder="비밀번호를 입력하세요">
							<div class="check-btn">
								<input class="site-btn" type="submit" value="탈퇴">
		                 		<input class="site-btn" type="reset" value="취소" onclick="history.back();">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function checkForm() {
			if (!agree.checked) {
				alert("탈퇴 안내를 확인하고 동의해 주세요.");
				return false;
			}

			if (pass.value == "") {
				alert("비밀번호를 입력해 주세요.");
				return false;
			}
		}
	</script>

	<!-- Register Form Section End -->
    
    <!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>
</body>
</html>