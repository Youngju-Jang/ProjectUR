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
                        <span>정보수정</span>
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
				<div
					class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
					<div class="filter-widget">
						<ul class="filter-catagories">
							<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
							<li> <a href="./MemberInfo.me">내 정보 보기</a></li>
							<li class="check-menu"><a href="./passCheck.me">내 정보 변경</a></li>
							<li> <a href="./MemberOrderList.me"> 주문 이력</a></li>
							<li> <a href="./MemberReviewList.me"> 나의 리뷰</a></li>
							<li> <a href="./MemberQnaList.me"> 상품 QnA</a></li>
							<li> <a href="./MemberDelete.me"> 회원탈퇴</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9 order-1 order-lg-2">
					<div class="login-form">
					
						<h2>비밀번호 재확인</h2>
						
						<!--정보 업데이트 시 한번더 패스체크  -->
						<%
						String idc = (String) session.getAttribute("id");
						if(idc == null) {
						%>
						<script>
						alert("해당 서비스는 로그인 후 이용 가능합니다.");
						location.href = "./Login.me";
						</script>
						<%
						}
						%>
						
						<form action="./passCheckAction.me" method="post">
							<div class="pass-check-form">
								<p>본인확인을 위해 한번 더 비밀번호를 입력해 주세요.</p>
								<table>
									<tr>
										<td>아이디</td>
										<td><input type="text" id="username" name="id" value="<%=id %>" readonly></td>
									</tr>
									<tr>
										<td>비밀번호</td>
										<td><input type="password" name="pass" id="pass" placeholder="비밀번호 입력"></td>
									</tr>
								</table>
								<div class="check-btn">
									<input class="site-btn" type="submit" value="확인">
		                 			<input class="site-btn" type="reset" value="취소" onclick="history.back();">
								</div>
							</div>
						</form>
						
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