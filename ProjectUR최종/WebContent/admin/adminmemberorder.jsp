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
    <%@include file="../inc/adminLoginCheck.jsp" %>
    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text product-more">
                        <a href="./index.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>주문 관리</span>
                    </div>                
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->
    
	<%
    		//request.setAttribute("ProductList", productList);
    	    List list = (List)request.getAttribute("list");
    	    OrderBean ob=(OrderBean)list.get(0);
    	%>

<script type="text/javascript">
function removeCheck() {

	 if (confirm("정말 삭제하시겠습니까?") == true){    //확인
		 location.href="./AdminOrderDeleteAction.ao?b_num=<%=ob.getB_num()%>";
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
                     		<li class="check-menu"> <a href="./AdminOrderList.ao"> 주문 관리</a></li>
                     		<li> <a href="./AdminQnaList.ao"> QnA 관리</a></li>
                        </ul>                    	
                    </div>
                </div> 

                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="cart-table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="b-num">주문번호</th>
                                    <th class="id">아이디</th>
                                    <th class="name">이름</th>
                                    <th class="date">주문일시</th>
                                    <th class="active">주문상태</th>
                                    <th class="button2"> </th>
                                </tr>
                            </thead>
                            <tbody>
                            
								<%
                            									for(int i=0;i<list.size();i++){
                            															ob=(OrderBean)list.get(i);
                            								%>
                                <tr>
                                	<td class="b-num">
                                        <%= ob.getB_num() %>
                                    </td>
                                    <td class="id">
                                        <%= ob.getId() %>
                                    </td>
                                    <td class="name">
                                        <%= ob.getO_name()%>
                                    </td>
                                    <td class="date">
                                        <%= ob.getB_date()%>
                                    </td>
                                    <%
                                    String result= "";
                                    if(ob.getD_result()==0){
                                    	result = "주문완료";
                                    }else if(ob.getD_result()==1){
                                    	result = "결제완료";
                                    }else if(ob.getD_result()==2){
                                    	result = "배송준비";
                                    }else if(ob.getD_result()==3){
                                    	result = "배송완료";
                                    }                                    
                                    %>
                                    <td class="active">
                                        <%= result %>
                                    </td>
                                    <td class="button2">
    								    <input type="button" class="site-btn update" value="변경" onclick="location.href='./AdminOrderDetail.ao?b_num=<%=ob.getB_num()%>&p_num=<%=ob.getP_num()%>';">
                                    	<input type="button" class="site-btn update" value="삭제" onclick="removeCheck()">
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