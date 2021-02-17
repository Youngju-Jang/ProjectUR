<%@page import="net.member.db.MemberBean"%>
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
                        <span>회원정보</span>
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
                    		<li> <a href="./MemberInfo.me"> 내 정보 보기</a></li>
                     		<li class="check-menu"> <a href="./MemberUpdate.me"> 내 정보 변경</a></li>
                    		<li> <a href="./MemberOrderList.me"> 주문 이력</a></li>
                    		<li> <a href="./MemberReviewList.me"> 나의 리뷰</a></li>
                    		<li><a href="./MemberQnaList.me"> 상품 QnA</a></li>
                    		<li> <a href="./MemberDelete.me"> 회원탈퇴</a></li> 
                        </ul>                    	
                    </div>
                </div>
                <div class="col-lg-9 order-1 order-lg-2">
                    <div class="member-update-form">
                    
                    
                        <h2>내 정보 변경</h2>
                        
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
</script>

						<%
                          MemberBean mb = (MemberBean) request.getAttribute("mb");
						String idc = (String) session.getAttribute("id");
						if (idc == null){response.sendRedirect("./Login.me");} 
   						 %>
   						 
   						 
                        <form action="./MemberUpdateAction.me" method="post">
                        	<table>
                        	  <tr>
                        	    <th>아이디 </th>
                        	    <td><%=mb.getId() %></td>
                        	  </tr>
                        	  <tr>
                        	    <th>비밀번호</th>
                        	    <td><input type="password" name="pass" id="pass"/></td>
                        	  </tr>
                        	  <tr>
                        	    <th>이메일 </th>
                        	    <td><input type="email" name="email" id="email" value="<%=mb.getEmail()%>" readonly></td>
                        	  </tr>
                        	  <tr>
                        	    <th>주소</th>
                        	    <td>
                        	      <input type="text" name="postcode" id="postcode" value="<%=mb.getPostcode()%>" readonly>
                        	      <input type="button" class="site-btn post" onclick="execDaumPostcode()" value="주소검색">
                        	      <input type="text" name="address1" id="address1" class="update" value="<%=mb.getAddress1()%>" readonly>
                        	      <input type="text" name="address2" id="address2" class="update" value="<%=mb.getAddress2()%>">
								</td>
                        	  </tr>
                        	</table>
							<div class="check-btn">
								<input class="site-btn" type="submit" value="수정">
								<input class="site-btn" type="reset" value="취소">
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