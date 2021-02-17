<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	
	<!-- 헤더 -->
    <%@include file="/inc/header.jsp" %>

    <!-- Hero Section Begin -->
    <section class="hero-section">
        <div class="hero-items owl-carousel">
            <div class="single-hero-items set-bg" data-setbg="img/banner01.png">
            </div>
            <div class="single-hero-items set-bg" data-setbg="img/banner02.png">
            </div>
            <div class="single-hero-items set-bg" data-setbg="img/banner03.png">
            </div>
            <div class="single-hero-items set-bg" data-setbg="img/banner04.png">
            </div>
            <div class="single-hero-items set-bg" data-setbg="img/banner05.png">
            </div>
        </div>
    </section>
    <!-- Hero Section End -->
    
    <!-- Man Banner Section Begin -->
    <section class="man-banner spad">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-8">
                    <div class="filter-control">
                    </div>
                    <div class="product-slider owl-carousel">
                      <%ArrayList mainList = (ArrayList)request.getAttribute("mainList"); %>
                    	<c:forEach var="list" items="${requestScope.mainList }">
						  <c:set var="ml" value="${pageScope.list }"/>
						  <div class="product-item">
							<div class="pi-pic">
		                      <img class="main_img" src="./upload/${ml.img_main}" alt="">
		                          <!-- <div class="sale">Sale</div> -->
		                          <ul>
		                            <!-- <li class="w-icon active"><a href="#"><i class="icon_bag_alt"></i></a></li> -->
		                            <li class="quick-view"><a href="./Product.p?p_num=${ml.p_num }">+ 상세보기</a></li>
		                          </ul>
							</div>
							<div class="pi-text">
							  <div class="catagory-name">
								<c:if test="${ml.category == 1 }">피로/간</c:if>
								<c:if test="${ml.category == 2 }">수면/스트레스</c:if>
								<c:if test="${ml.category == 3 }">피부</c:if>
								<c:if test="${ml.category == 4 }">눈</c:if>
								<c:if test="${ml.category == 5 }">두뇌활동</c:if>
								<c:if test="${ml.category == 6 }">심장/혈관/혈당</c:if>
							  </div>
							  <a href="./Product.p?p_num=${ml.p_num }">
							    <h5>
								  <c:choose>
								    <c:when test="${fn:length(ml.p_name ) gt 20}">
									  <c:out value="${fn:substring(ml.p_name , 0, 19)}"/>...
								    </c:when>
								    <c:otherwise>
									  <c:out value="${ml.p_name }"/>
								    </c:otherwise>
								  </c:choose>
							    </h5>
							  </a>
							  <div class="product-price">
							    <fmt:formatNumber value="${ml.p_saleprice}" pattern="#,###" />원
							    <span><fmt:formatNumber value="${ml.p_price}" pattern="#,###" />원</span>
							  </div>
						    </div>
						  </div>
						</c:forEach>
                    </div>
                </div>
                <div class="col-lg-3 offset-lg-1">
                    <div class="product-large set-bg m-large" data-setbg="img/banner08.png">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Man Banner Section End -->

    <!-- 푸터 -->
    <%@include file="inc/footer.jsp" %>

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.countdown.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery.zoom.min.js"></script>
    <script src="js/jquery.dd.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>