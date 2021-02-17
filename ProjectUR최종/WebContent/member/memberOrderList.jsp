<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.member.db.MemberDAO"%>
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
int count = (Integer)request.getAttribute("count");
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
                    		<li class="check-menu"> <a href="./MemberOrderList.me"> 주문 이력</a></li>
                    		<li> <a href="./MemberReviewList.me"> 나의 리뷰</a></li>
                    		<li> <a href="./MemberQnaList.me"> 상품 QnA</a></li>
                    		<li> <a href="./MemberDelete.me"> 회원탈퇴</a></li> 
                        </ul>                    	
                    </div>
                </div>

                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="cart-table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="num">주문번호</th>
                                    <th class="product">상품명</th>
                                    <th class="date">주문일시</th>
                                    <th class="result">상태</th>
                                </tr>
                            </thead>
                            <tbody>
								<!-- 주문 내역이 없을 경우 -->
								<%if(count==0){	%>
								<tr>
									<td colspan="5"><h2>주문 내역이 없습니다</h2></td>
								</tr>
								<%}	%>
                               <%
								MemberDAO mdao = new MemberDAO();
								ArrayList<Map<String, Object>> list = mdao.getMemberOrderDetail(id);
								for(Map<String, Object> m : list){
								%>
                                <tr>
                                	<td class="num">
                                        <%=m.get("b_num") %>
                                    </td>
                                    <td class="product">
                                        <a href="./MemberOrderDetail.me?b_num=<%=m.get("b_num")%>"><%=m.get("p_name") %></a>
                                    </td>
                                    <td class="date">
                                        <%=m.get("b_date")%>
                                    </td>
                                    <%
                                    String result= "";
                                    if(m.get("d_result").equals(0)){
                                    	result = "주문완료";
                                    }else if(m.get("d_result").equals(1)){
                                    	result = "결제완료";
                                    }else if(m.get("d_result").equals(2)){
                                    	result = "배송준비";
                                    }else if(m.get("d_result").equals(3)){
                                    	result = "배송완료";
                                    }                                    
                                    %>
                                    <td class="result">
                                        <%= result %>
                                    </td>
    							</tr>	   
                                <%
   								}
                                %>
                            </tbody>
                        </table>
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