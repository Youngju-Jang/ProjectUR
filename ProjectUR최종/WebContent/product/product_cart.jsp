<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.product.db.ProductBean"%>
<%@page import="net.cart.db.CartBean"%>
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
                        <span>장바구니</span>
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
//한글처리
request.setCharacterEncoding("UTF-8");
//basketList에서 구현한 정보를 전달받아서 처리
ArrayList<CartBean> cartList = (ArrayList<CartBean>) request.getAttribute("cartList");
int totalPrice = 0; //장바구니 총 합계금액

%>
    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart-table">
                        <table>
                        	<colgroup>
								<col width="5%" />
								<col width="10%" />
								<col width="*" />
								<col width="5%" />
								<col width="10%" />
								<col width="10%" />
								<col width="5%" />
							</colgroup>
                        <!-- 장바구니 번호, 사진(대표이미지), 상품명, 사이즈,색상,수량,가격, 삭제 -->
                            <thead>
                                <tr>
								   <th>번호</th>
								   <th>사진</th>
								   <th class="p-name">상품명</th>
								   <!-- th>크기</th-->
								   <!-- th>색상</th-->
								   <th>수량</th>
								   <th>가격</th>
								   <th>합계</th>
								   <th width="100px">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
									<%
									if(cartList.size() ==0) {
									%>
								 		<tr><td colspan="7">장바구니가 비었습니다.</td></tr>
								 	<% } else {
								 	
								   //EL태그는 FOR문과 상관없이 <c:forEach> 사용해서 출력해야한다.
								   for(int i=0; i<cartList.size(); i++){
									   CartBean cb = (CartBean) cartList.get(i);
									   ProductBean pb = cb.getProducts();
									   totalPrice = totalPrice + (pb.getP_saleprice() * cb.getP_count());
								   %>
								      	
                                <tr>
                                	<td class="first-row"><%=i+1%></td><!-- 순차 넘버링  -->
                                    <td class="cart-pic first-row"><img class="main_img" src="./upload/<%=pb.getImg_main().split(",")[0] %>" height="100" width="170"></td>
                                    <td class="cart-title first-row">
                                        <h5><%=pb.getP_name() %></h5>
                                    </td>
                                    <td class="first-row">
                                        <!-- div class="quantity">
                                            <div class="pro-qty">
                                                <input type="text" value="1">
                                            </div>
                                        </div-->
                                        <%=cb.getP_count() %>
                                    </td>
                                    <td class="first-row"><script type="text/javascript">numberWithComma(<%=pb.getP_saleprice() %>)</script></td>
                                    <td class="first-row"><script type="text/javascript">numberWithComma(<%=pb.getP_saleprice()*cb.getP_count()%>)</script></td>
                                    <td class="close-td first-row" style="padding-left:20px;padding-right:20px"><a href="./CartDeletePart.ba?c_num=<%=cb.getC_num()%>"><i class="ti-close"></i></a></td>
                                </tr>
                                <%} 
                                }%>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="cart-buttons">
                                <a href="./CartDelete.ba" class="primary-btn up-cart">장바구니 비우기</a>
                                <a href="./ProductList.p?category=0" class="primary-btn continue-shop">쇼핑 계속하기</a>
                            </div>
                        </div>
                        <div class="col-lg-4 offset-lg-4">
                            <div class="proceed-checkout">
                                <ul>
                                    <li class="cart-total">합계금액 <span><script type="text/javascript">numberWithComma(<%=totalPrice %>)</script></span></li>
                                </ul>
                                <% if (totalPrice==0) { %>
                                <a href="javascript:alert('장바구니가 비었습니다');" class="proceed-btn">결제하기</a>
                                <%}else{%>
                                <a href="./OrderStar.or" class="proceed-btn">결제하기</a>
                                <%} %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
<!-- 
<input type="button" class="btn" value="[구매하기]" onclick="location.href='./OrderStar.or'">
<input type="button" class="btn" value="[계속 쇼핑하기]" onclick="location.href='./ProductList.go'">
 -->
    <!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>
    
