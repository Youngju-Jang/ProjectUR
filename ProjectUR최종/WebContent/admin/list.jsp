<%@page import="java.util.List"%>
<%@page import="net.product.db.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>uR</title>

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
    <%@include file="/inc/header.jsp" %>
	<%@include file="/inc/adminLoginCheck.jsp" %>
    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text product-more">
                        <a href="./index.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>상품 관리</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->
	<%
	//request.setAttribute("ProductList", productList);
	List productList = (List)request.getAttribute("productList");
	ProductBean pb = (ProductBean)productList.get(0);
	%>


    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
            	<div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
                    <div class="filter-widget">
                        <ul class="filter-catagories">
                        	<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
                    		<li> <a href="./AdminMember.ao"> 회원 관리 </a></li>
                     		<li class="check-menu"> <a href="./ProductList.ap"> 상품 관리</a></li>
                     		<li> <a href="./AdminOrderList.ao"> 주문 관리</a></li>
                     		<li> <a href="./AdminQnaList.ao"> QnA 관리</a></li>
                        </ul>                    	
                    </div>
                </div>            
                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="cart-table">
						<input type="button" class="site-btn add" value="상품 등록" onclick="location.href='./ProductAdd.ap';">
                        <table>
                            <thead>
                                <tr>
                                    <th class="p-num">번호</th>
                                    <!-- <th>카테고리</th> -->
                                    <th>이미지</th>
                                    <th class="p-name">상품명</th>
                                    <th>가격</th>
                                    <th class="p-count">재고</th>
                                    <!-- <th>수정/삭제</th> -->
                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>
                            
                               <%
   								for(int i=0;i<productList.size();i++){
   								pb = (ProductBean)productList.get(i);
   									
	   							String price = Integer.toString(pb.getP_saleprice());
	   							StringBuffer sb = new StringBuffer(price);
	   							String count = Integer.toString(pb.getP_count());
	   							StringBuffer sb1 = new StringBuffer(count);
	   							if(price.length()==4){
                                sb.insert(1,',');
	   							}else if(price.length()==5){
	   							sb.insert(2,',');	
	   							}else if(price.length()==6){
	   							sb.insert(3,',');	
	   							}else if(price.length()==7){
	   							sb.insert(1,',');	
	   							sb.insert(4,',');	
	   							}else if(price.length()==8){
	   							sb.insert(5,',');
	   							sb.insert(2,',');
	   							}else if(price.length()==9){
	   							sb.insert(6,',');
	   							sb.insert(3,',');
	   							}
	   							if(count.length()==4){
                                sb1.insert(1,',');
	   							}else if(count.length()==5){
	   							sb1.insert(2,',');	
	   							}else if(count.length()==6){
	   							sb1.insert(3,',');	
	   							}else if(count.length()==7){
	   							sb1.insert(1,',');	
	   							sb1.insert(4,',');	
	   							}else if(count.length()==8){
	   							sb1.insert(5,',');
	   							sb1.insert(2,',');
	   							}else if(count.length()==9){
	   							sb1.insert(6,',');
	   							sb1.insert(3,',');
	   							}
	   							if(pb.getAction()!=1){
	   						   %>
                                <tr>
                                	<td class="p-num">
                                        <%= pb.getP_num() %>
                                    </td>
                                    <%
                                    String result = "";
                                    if(pb.getCategory()==1){
                                    	result="피로/간";
                                    }else if(pb.getCategory()==2){
                                    	result="수면/스트레스";
                                    }else if(pb.getCategory()==3){
                                    	result="피부";
                                    }else if(pb.getCategory()==4){
                                    	result="눈";
                                    }else if(pb.getCategory()==5){
                                    	result="두뇌활동";
                                    }else if(pb.getCategory()==6){
                                    	result="심장/혈관/혈당";
                                    }
                                    %>
                                     
                                    <td class="cart-pic first-row">
                                    <%if(pb.getP_count()==0){ %>
                                    <img src="./upload/1.jfif" alt="">
                                    <%}else{ %>
                                    
                                    <img class="main_img" src="./upload/<%=pb.getImg_main() %>" alt="" width="180" height="125">
                                    <%} %>
                                    </td>
                                    
                                    <td class="cart-title">
                                    	<div class="p-cate"><%= result %><br></div>
                                        <a href="./Product.p?p_num=<%=pb.getP_num()%>"><%= pb.getP_name() %></a>
                                    </td>
                                    <td class="p-price"><%= sb %>원</td>
                                	<td class="p-count">
                                        <%= sb1 %>
                                    </td>
                                    <td>
                                    	<input type="button" class="site-btn update" value="변경" onclick="location.href='./ProductModify.ap?p_num=<%=pb.getP_num()%>';"><br>
                                    	<input type="button" class="site-btn update" value="삭제" onclick="location.href='./ProductDeleteAction.ap?p_num=<%=pb.getP_num()%>';">
    								</td>
    							</tr>	   
                                <%
	   							}
   								}
                                %>
                            </tbody>
                        </table>
                    </div>
                    <!-- 
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="cart-buttons">
                                <a href="#" class="primary-btn continue-shop"><h6>* 주의사항</h6></a>
                                <a href="#" class="primary-btn up-cart"><span>*이미지 파일은 수정할 수 없습니다.</span></a>
                                <a href="#" class="primary-btn up-cart"><span>*수정을 원하시면 삭제하고 상품을 등록하십시오.</span></a>
                            </div>
                            <div class="discount-coupon"></div>
                        </div>
                    </div>
                     -->
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
	<!-- 푸터 -->
    <%@include file="/inc/footer.jsp" %>
</body>
</html>