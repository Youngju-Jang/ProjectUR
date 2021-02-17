<%@page import="net.member.db.MemberDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.product.db.ProductBean"%>
<%@page import="net.order.db.OrderBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
    <!-- Breadcrumb Section Begin -->    
<%
	request.setCharacterEncoding("UTF-8");
	int p_num = (Integer)request.getAttribute("p_num");
	String idc = (String) session.getAttribute("id");
	if (idc == null){response.sendRedirect("./Login.me");} 
    %>
 	
    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
        	<div class="row">
            	<div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
                    <div class="filter-widget">
                        <ul class="filter-catagories">
                        	<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
                    		<li> <a href="./MemberInfo.me"> 내 정보 보기</a></li>
                     		<li> <a href="./passCheck.me"> 내 정보 변경</a></li>
                    		<li> <a href="./MemberOrderList.me"> 주문 이력</a></li>
                    		<li class="check-menu"> <a href="./MemberReviewList.me"> 나의 리뷰</a></li>
                    		<li> <a href="./MemberQnaList.me"> 상품 QnA</a></li>
                    		<li> <a href="./MemberDelete.me"> 회원탈퇴</a></li> 
                        </ul>                    	
                    </div>
                </div>
                
                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="review-table">
                      <form action="./MemberReviewWriteAction.me?p_num=<%=p_num %>" method="post" name="review">
                        <input type="hidden" name="id" value="<%=id %>">
                        <input type="hidden" name="p_num" value="<%=p_num %>">
                        <table>
                          <tr>
                            <th class="r_star">고객 만족도</th>
                            <td>
                              <div class="rating">
                                <select id="r_star" name="r_star" required>
								  <option value="5" selected="selected">★★★★★ </option>
								  <option value="4">★★★★☆ </option>
								  <option value="3">★★★☆☆ </option>
								  <option value="2">★★☆☆☆ </option>
								  <option value="1">★☆☆☆☆ </option>
                                </select>
                              </div>
                            </td>
                          </tr>
                          <tr>
                            <th class="r_content">리뷰 작성</th>
                            <td><textarea id="r_content" name="r_content" placeholder="리뷰 내용을 작성해주세요" required></textarea></td>
                          </tr>
                        </table>
                        <div class="check-btn">
                          <input class="site-btn review" type="submit" value="등록하기">
                          <input class="site-btn review" type="button" value="취소하기" onclick="history.back();">
                        </div>
                      </form>
					</div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
    
	<!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>
</body>
</html>