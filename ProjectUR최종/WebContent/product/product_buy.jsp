<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.cart.db.CartBean"%>
<%@page import="net.member.db.MemberBean"%>
<%@page import="net.product.db.ProductBean"%>
<%@page import="java.util.ArrayList"%>
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
                    <div class="breadcrumb-text product-more">
                        <a href="./"><i class="fa fa-home"></i> Home</a>
                        <span>주문</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->
<script type="text/javascript">
//세자리 수 콤마
function numberWithComma(x) {
	document.write(x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

</script>    
<%
//OrderStarAction에 저장한 정보를 가져오기
//장바구니정보 저장된 거 가져오기
ArrayList<CartBean> cartList = (ArrayList<CartBean>) request.getAttribute("cartList");
int totalPrice = 0; //장바구니 총 합계금액
MemberBean mb = (MemberBean)request.getAttribute("memberBean");
%>
    <!-- Shopping Cart Section Begin -->
    <section class="checkout-section spad">
        <div class="container">
            <form action="./OrderAdd.or" class="checkout-form" action="POST">
                <div class="row">
                    <div class="col-lg-6">
                        <h4>주문 정보</h4>		
                        <div class="row">
                            <div class="col-lg-6">
                                <label for="fir">주문자 이름<span>*</span></label>
                                <input type="text" id="o_name" name="o_name">
                            </div>
                            <div class="col-lg-6">
                                <label for="last">전화번호<span>*</span></label>
                                <input type="text" id="o_phone" name="o_phone">
                            </div>
                            <div class="col-lg-12">
                                <label for="cun-name">이메일</label>
                                <input type="text" id="o_email" name="o_email" value="<%=mb.getEmail()%>">
                            </div>
                            <div class="col-lg-6">
                                <label for="fir">받는분 이름<span>*</span></label>
                                <input type="text" id="d_name" name="d_name">
                            </div>
                            <div class="col-lg-6">
                                <label for="last">받는분 전화번호<span>*</span></label>
                                <input type="text" id="d_phone" name="d_phone">
                            </div>
                            <div class="col-lg-12">
                                <label for="zip">우편번호</label>
                                <input type="text" id="d_postcode" name="d_postcode" value="<%=mb.getPostcode() %>">
                            </div>
                            <div class="col-lg-12">
                                <label for="street">주소<span>*</span></label>
                                <input type="text" id="d_address" class="street-first" name="d_address" value="<%=mb.getAddress1()%>">
                                <input type="text" id="d_address2" name="d_address2" value="<%=mb.getAddress2()%>">
                            </div>

                            <div class="col-lg-6">
                                <label for="phone">메세지<span>*</span></label>
                                <input type="text" id="d_message" name="d_message">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
  
                        <div class="place-order">
                            <h4>주문 내용</h4>
                            <div class="order-total">
                                <ul class="order-table">
                                    <li>주문상품 <span>합계</span></li>
                                   	<%
                                    	for(int i=0; i<cartList.size(); i++){
									   		CartBean cb = (CartBean) cartList.get(i);
									   		ProductBean pb = cb.getProducts();
									   		totalPrice = totalPrice + (pb.getP_saleprice() * cb.getP_count());
								   			if(cb.getIs_direct()) { //바로구매이면 %>
								   	<input type="hidden" name="p_num" value="<%=cb.getP_num()%>">
								   	<input type="hidden" name="p_count" value="<%=cb.getP_count()%>">
								   	<% }  %>
                                    <li class="fw-normal"><%=pb.getP_name()%> * <%=cb.getP_count() %>개 <span><script type="text/javascript">numberWithComma(<%=pb.getP_saleprice()*cb.getP_count()%>)</script></span></li>
                                    <%} %>
                                    <li class="total-price">합계금액 <span><script type="text/javascript">numberWithComma(<%=totalPrice %>)</script></span></li>
                                </ul>
                                <div class="payment-check">
                                    <div class="pc-item">
                                        <label for="pc-check">
                                            	무통장입금(한국은행 : 12-345-3456 유알)
                                            <input type="checkbox" id="pc-check" name="payment" value="1" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>

                                </div>
                                <div class="order-btn">
                                    <button type="submit" class="site-btn place-btn">주문하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
    <%@include file="../inc/footer.jsp" %>