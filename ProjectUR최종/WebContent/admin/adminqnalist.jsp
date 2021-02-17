<%@page import="net.product.db.ProductQnaBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<%@include file="../inc/adminLoginCheck.jsp" %>
    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text product-more">
                        <a href="./index.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>QnA 관리</span>
                    </div>                
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->
	<%
	//request.setAttribute("ProductList", productList);
	List list = (List)request.getAttribute("list");
	ProductQnaBean qb=(ProductQnaBean)list.get(0);
	%>
<script type="text/javascript">
function QnACheck() {

	 if (confirm("답변하시겠습니까?") == true){    //확인
		 window.open("./Product.p?p_num=<%=qb.getP_num()%>#tab-3");
		 
	 }else{   //취소
	     return false;
	 }
}
</script>
    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
            	<div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
                    <div class="filter-widget">
                        <ul class="filter-catagories">
                        	<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
                    		<li> <a href="./AdminMember.ao"> 회원 관리</a></li>
                     		<li> <a href="./ProductList.ap"> 상품 관리</a></li>
                     		<li> <a href="./AdminOrderList.ao"> 주문 관리</a></li>
                     		<li class="check-menu"> <a href="./AdminQnaList.ao"> QnA 관리</a></li>
                        </ul>                    	
                    </div>
                </div> 

                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="cart-table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="num">상품번호</th>
                                    <th class="id">아이디</th>
                                    <th class="date">작성시간</th>
                                    <th class="result">답변확인</th>
                                    <th class="button2"> </th>
                                </tr>
                            </thead>
                            <tbody>
                                                           <%
								for(int i=0;i<list.size();i++){
									qb=(ProductQnaBean)list.get(i);
								%>
                                <tr>
                                    <td class="num">
                                        <%= qb.getP_num() %>
                                    </td>
                                	<td class="id">
                                        <%= qb.getId() %>
                                    </td>
                                    <td class="date">
                                        <%= qb.getReg_date()%>
                                    </td>
                                    <%
                                    if(qb.getRe_result()==0){
                                    	String result = "미답변";
                                    %>
                                    <td class="result">
                                        <%= result %>
                                    </td>
									<%} %>                                    
                                    <td class="button2">
                                    	<input type="button" class="site-btn update" value="답변" onclick="QnACheck()" >
    								</td>
    							</tr>	   
    							<%}%>
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