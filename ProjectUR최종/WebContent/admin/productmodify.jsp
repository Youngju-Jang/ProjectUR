<%@page import="net.product.db.ProductBean"%>
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
 	<%@include file="../inc/adminLoginCheck.jsp" %>
    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="./index.jsp"><i class="fa fa-home"></i> Home</a>
                        <a href="./ProductList.ap">상품 관리</a>
                        <span>상품 변경</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Form Section Begin -->
	<%
	//request.setAttribute("ProductBean", pb);
	ProductBean pb = (ProductBean)request.getAttribute("pb");
	%>
	
    <!-- Register Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
            	<div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
                    <div class="filter-widget">
                        <ul class="filter-catagories">
                        	<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
                    		<li> <a href="./AdminMember.ao"> 회원 관리</a></li>
                     		<li class="check-menu"> <a href="./ProductList.ap"> 상품 관리</a></li>
                     		<li> <a href="./AdminOrderList.ao"> 주문 관리</a></li>
                     		<li> <a href="./AdminQnaList.ao"> QnA 관리</a></li>
                        </ul>                    	
                    </div>
                </div>
                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="register-product-form">
                        <h2>상품 변경 등록</h2>
                        <form action="./ProductModifyAction.ap" method="post" enctype="multipart/form-data">
                        	
                        	<input type="hidden" value="<%= pb.getP_num() %>" name="p_num">
	                 		<table>
	                 		  <tr>
	                 		    <th><label for="category">카테고리</label></th>
	                 		    <td>
	                        	  <select name="category"  id="category">
	                        		<option value="1" <%if(pb.getCategory()==1){%>selected<%}%>>피로/간</option>
	                        		<option value="2" <%if(pb.getCategory()==2){%>selected<%}%>>수면/스트레스</option>
	                        		<option value="3" <%if(pb.getCategory()==3){%>selected<%}%>>피부</option>
	                        		<option value="4" <%if(pb.getCategory()==4){%>selected<%}%>>눈</option>
	                        		<option value="5" <%if(pb.getCategory()==5){%>selected<%}%>>두뇌활동</option>
	                        		<option value="6" <%if(pb.getCategory()==6){%>selected<%}%>>심장/혈관/혈당</option>
								  </select>                		    
	                 		    </td>
	                 		    <th><label for="p_name">상품명</label></th>
	                 		    <td colspan="3"><input type="text" id="p_name" name="p_name" value="<%=pb.getP_name() %>"></td>
	                 		  </tr>
	                 		  <tr>
	                 		    <th><label for="p_price">가격</label></th>
	                 		    <td><input type="number" id="p_price" name="p_price" value="<%= pb.getP_price() %>" min=0></td>
	                 		    <th><label for="p_saleprice">할인가</label></th>
	                 		    <td><input type="number" id="p_saleprice" name="p_saleprice" value="<%= pb.getP_saleprice() %>" min=0></td>
	                 		    <th><label for="p_count">수량</label></th>
	                 		    <td><input type="number" id="p_count" name="p_count" value="<%= pb.getP_count() %>" min=0></td>
	                 		  </tr>
	                 		  <tr>
	                 		    <th><label for="img_main">메인 이미지</label></th>
	                 		    <td colspan="5"><input type="file" id="img_main" name="img_main" value="<%=pb.getImg_main()%>"></td>
	                 		  </tr>
	                 		  <tr>
	                 		    <th><label for="file1">서브 이미지</label></th>
	                 		    <td colspan="5"><input type="file" id="file1" name="file1"></td>
	                 		  </tr>
	                 		  <tr>
	                 		    <th><label for="file2">서브 이미지</label></th>
	                 		    <td colspan="5"><input type="file" id="file2" name="file2"></td>
	                 		  </tr>
	                 		  <tr>
	                 		    <th><label for="file3">서브 이미지</label></th>
	                 		    <td colspan="5"><input type="file" id="file3" name="file3"></td>
	                 		  </tr>
	                 		  <tr>
	                 		    <th><label for="file4">서브 이미지</label></th>
	                 		    <td colspan="5"><input type="file" id="file4" name="file4"></td>
	                 		  </tr>
	                 		</table>
                            <div class="cart-buttons">
                                <a href="#" class="primary-btn up-cart"><span>*수정 시 이미지 파일을 다시 업로드 해 주십시오.</span></a>
                            </div>
	                 		<div class="check-btn">
	                 			<input class="site-btn" type="submit" value="변경하기">
	                 			<input class="site-btn" type="reset" value="취소" onclick="history.back();">
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
</body>
</html>