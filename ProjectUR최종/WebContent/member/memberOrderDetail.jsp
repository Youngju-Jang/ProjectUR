<%@page import="java.util.List"%>
<%@page import="net.product.db.ProductBean"%>
<%@page import="net.order.db.OrderBean"%>
<%@page import="net.cart.db.CartBean"%>
<%@page import="java.util.ArrayList"%>
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
<script type="text/javascript">
//세자리 수 콤마
function numberWithComma(x) {
	document.write(x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
</script>    
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
int totalPrice = 0; //장바구니 총 합계금액
// request.setAttribute("orderList", totalList.get(0));
// request.setAttribute("productList", totalList.get(1));
List orderList = (List)request.getAttribute("orderList");
List productList = (List)request.getAttribute("productList");
String idc = (String) session.getAttribute("id");
if (idc == null){response.sendRedirect("./Login.me");}  
%>
    <!-- Shopping Cart Section Begin -->
    <section class="checkout-section spad">
        <div class="container">
            <form action="#" class="checkout-form" action="POST">
                <div class="row">
                <%for(int i=0;i<orderList.size();i++){ 
                	OrderBean ob = (OrderBean)orderList.get(i);
                	ProductBean pb = (ProductBean)productList.get(i);
                %>
                    <div class="col-lg-6">
                        <h4>주문 정보</h4>		
                        <div class="row">
                            <div class="col-lg-6">
                                <label for="fir">주문자 이름</label>
                                <input type="text" id="o_name" name="o_name" value="<%=ob.getO_name()%>" readonly>
                            </div>
                            <div class="col-lg-6">
                                <label for="last">전화번호</label>
                                <input type="text" id="o_phone" name="o_phone" value="<%=ob.getO_phone()%>" readonly>
                            </div>
                            <div class="col-lg-12">
                                <label for="cun-name">이메일</label>
                                <input type="text" id="o_email" name="o_email" value="<%=ob.getO_email()%>" readonly>
                            </div>
                            <div class="col-lg-6">
                                <label for="fir">받는분 이름</label>
                                <input type="text" id="d_name" name="d_name" value="<%=ob.getD_name()%>" readonly>
                            </div>
                            <div class="col-lg-6">
                                <label for="last">받는분 전화번호</label>
                                <input type="text" id="d_phone" name="d_phone" value="<%=ob.getD_phone()%>" readonly>
                            </div>
                            <div class="col-lg-12">
                                <label for="zip">우편번호</label>
                                <input type="text" id="d_postcode" name="d_postcode" value="<%=ob.getD_postcode()%>" readonly>
                            </div>
                            <div class="col-lg-12">
                                <label for="street">주소</label>
                                <input type="text" id="d_address" class="street-first" name="d_address" value="<%=ob.getD_address()%>" readonly>
                                <input type="text" id="d_address2" name="d_address2" value="<%=ob.getD_address2()%>" readonly>
                            </div>
                            <div class="col-lg-12">
                                <label for="phone">메세지</label>
                                <input type="text" id="d_message" name="d_message" value="<%=ob.getD_message()%>" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="place-order">
                            <h4>주문 내용</h4>
                            <div class="order-total">
                                <ul class="order-table">
                                    <li>주문상품 <span>합계</span></li>
                                   	<% totalPrice = totalPrice + (pb.getP_saleprice() * ob.getB_count());%>
                                    <li class="fw-normal"><%=pb.getP_name()%> x <%=ob.getB_count() %> <span><script type="text/javascript">numberWithComma(<%=pb.getP_saleprice()*ob.getB_count()%>)</script></span></li>
                <%} %>
                                    <li class="total-price">합계금액 <span><script type="text/javascript">numberWithComma(<%=totalPrice %>)</script></span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>
</body>
</html>