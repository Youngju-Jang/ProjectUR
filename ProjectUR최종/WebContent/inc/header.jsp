<%@page import="net.cart.db.CartDAO"%>
<%@page import="net.cart.db.CartBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("id");
%>
<header class="header-section">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


	<div class="header-top">
		<div class="container">
			<div class="ht-right">
			  <ul>
			<%if(id == null || id.isEmpty()) { %>
				<li><a href="./Login.me" class="login-panel"><i class="fa fa-user"></i>Login</a></li>
				<li><a href="./Logout.me" class="login-panel"><i class="fa fa-user"></i>Logout</a></li>
				<li><a href="./Join.me" class="login-panel"><i class="fa fa-user"></i>Join</a></li>
			<%}else if(id.equals("admin")){	%>
				<!-- id 클릭시 관리자 페이지로 이동. jsp 페이지명 변경될 수 있음 -->
				<li class="menu-page"><a href="./AdminMenu.ao" class="login-panel">관리자님 환영합니다.</a></li>
				<li><a href="./Logout.me" class="login-panel"><i class="fa fa-user"></i>Logout</a></li>
			<%}else{ %>
				<!-- id 클릭시 회원정보 페이지로 이동 -->			
				<li class="menu-page"><a href="./MemberInfo.me" class="login-panel menu-page"><%=id %>님 환영합니다.</a></li>
				<li><a href="./Logout.me" class="login-panel"><i class="fa fa-user"></i>Logout</a></li>
			<%} %>
			  </ul>			
			</div>
		</div>
	</div>
	<div class="container">
		<div class="inner-header">
			<div class="row">
				<div class="col-lg-2 col-md-2">
					<div class="logo">
						<a href="./Main.me">
							<img src="./img/logo_sample1.png" alt="logo">
						</a>
					</div>
				</div>
				
				<!-- 검색창  -->
				<div class="col-lg-7 col-md-7">
					<form action="./ProductList.p?category=0/board/board.jsp" method="get">
						<%@include file="search.jsp" %>
					</form>
				</div>
				<!-- 검색창  -->
				
				<div class="col-lg-3 text-right col-md-3">
					<ul class="nav-right">
					
					
						<%
							ArrayList<CartBean> HcartList = null;
							CartDAO Hcdao = null;
							if(id != null){
								Hcdao = new CartDAO();
								HcartList = Hcdao.getCartList(id);
								pageContext.setAttribute("HcartList", HcartList);		
							}
							%>
						
						<li class="cart-icon">
							<a href="./CartList.ba" style="font-size: 18px; font-weight: 700; color: #252525;">My Cart</a>
							<a href="./CartList.ba">
								<i class="icon_bag_alt"></i>
								<c:if test="${sessionScope.id == null || HcartList.size() == 0 }"><span>0</span></c:if>
								<c:if test="${HcartList.size() > 0 }"><span>${HcartList.size()}</span></c:if>
							</a>
							<div class="cart-hover">
								<div class="select-items">
									<table>
										<tbody><!--장바구니 제품  -->
											<c:set value="0" var="Htotal"/>
											<c:forEach items="${pageScope.HcartList}" var="hcb">
											<c:set value="${hcb.products }" var="hpb"/>
											<c:set value="${hcb }" var="hcbDelete"/>
											<c:set value="${hcb.c_num}" var="deleteNum"/>
											<tr>
												<td class="si-pic"><img src="./upload/${hpb.img_main }" alt="" width="70" height="70"></td>
												<td class="si-text">
													<div class="product-selected">
														<h6>${hpb.p_name }</h6>
														<p><fmt:formatNumber value="${hpb.p_price}" pattern="#,###" />원 x ${hcb.p_count }개</p>
													</div>
												</td>
												<!-- <td class="si-close">
													<i class="ti-close" ></i>
												</td>  -->
											</tr>
											<c:set var="Htotal" value="${hpb.p_saleprice*hcb.p_count + Htotal }"/>
											</c:forEach>
											
										</tbody>
									</table>
								</div>
								<div class="select-total">
									<span>total:</span>
									<h5><fmt:formatNumber value="${Htotal}" pattern="#,###"/>원</h5>
								</div>
								<div class="select-button">
									<a href="./CartList.ba" class="primary-btn checkout-btn">장바구니로</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="nav-item">
		<div class="container">
			<nav class="nav-menu mobile-menu">
				<ul>
					<li id="m01"><a href="./ProductList.p?category=0">전체</a></li>
					<li id="m01"><a href="./ProductList.p?category=1">피로/간</a></li>
					<li id="m02"><a href="./ProductList.p?category=2">수면/스트레스</a></li>
					<li id="m03"><a href="./ProductList.p?category=3">피부</a></li>
					<li id="m04"><a href="./ProductList.p?category=4">눈</a></li>
					<li id="m05"><a href="./ProductList.p?category=5">두뇌활동</a></li>
					<li id="m06"><a href="./ProductList.p?category=6">심장/혈관/혈당</a></li>
				</ul>
			</nav>
			<div id="mobile-menu-wrap"></div>
		</div>
	</div>
	
	

</header>