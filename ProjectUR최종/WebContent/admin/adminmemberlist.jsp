<%@page import="net.member.db.MemberBean"%>
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
                        <span>회원 관리</span>
                    </div>                
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->
	<%
	//request.setAttribute("ProductList", productList);
	List list = (List)request.getAttribute("list");
	MemberBean mb=(MemberBean)list.get(0);
	%>

    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
            	<div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 products-sidebar-filter">
                    <div class="filter-widget">
                        <ul class="filter-catagories">
                        	<!-- 해당 페이지의 메뉴에만 class="check-menu" 적용 -->
                    		<li class="check-menu"> <a href="./AdminMember.ao"> 회원 관리</a></li>
                     		<li> <a href="./ProductList.ap"> 상품 관리</a></li>
                     		<li> <a href="./AdminOrderList.ao"> 주문 관리</a></li>
                     		<li> <a href="#"> QnA 관리</a></li>
                        </ul>                    	
                    </div>
                </div> 

                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="cart-table">
                    	<div class="member-count">
                    		<h5>총 회원 수 : <%= list.size() %>명 </h5>
                    	</div>
                        <table>
                            <thead>
                                <tr>
                                    <th class="id">아이디</th>
                                    <th class="email">이메일</th>
                                    <th class="buy">누적 구매수</th>
                                    <th class="point">누적 포인트</th>
                                    <th class="price">누적 구매액</th>
                                    <th class="button2"> </th>
                                </tr>
                            </thead>
                            <tbody>
                            
                               <%
                               
   								for(int i=0;i<list.size();i++){
	   								mb=(MemberBean)list.get(i);
	   								
	   								String price = Integer.toString(mb.getTotalprice());
	   								StringBuffer sb = new StringBuffer(price);
	   								String point = Integer.toString(mb.getPoint());
	   								StringBuffer sb1 = new StringBuffer(point);
	   							
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
		   							if(point.length()==4){
	                                sb1.insert(1,',');
		   							}else if(point.length()==5){
		   							sb1.insert(2,',');	
		   							}else if(point.length()==6){
		   							sb1.insert(3,',');	
		   							}else if(point.length()==7){
		   							sb1.insert(1,',');	
		   							sb1.insert(4,',');	
		   							}else if(point.length()==8){
		   							sb1.insert(5,',');
		   							sb1.insert(2,',');
		   							}else if(point.length()==9){
		   							sb1.insert(6,',');
		   							sb1.insert(3,',');
		   							}
		   							if(mb.getAction()!=1){
	   						   %>
                                <tr>
                                	<td class="id">
                                        <a href="./AdminMemberOrder.ao?id=<%= mb.getId() %>">
                                        <%= mb.getId() %>
                                        </a>
                                    </td>
                                    <td class="email">
                                        <%= mb.getEmail() %>
                                    </td>
                                    <td class="buy">
                                        <%= mb.getPostcode()%>
                                    </td>
                                    
                                    <td class="point">
                                        <%= sb1 %>
                                    </td>
                                    
                                    <td class="price">
                                        <%= sb %>원
                                    </td>
                                    <%if(!mb.getId().equals("admin")){  %>
                                    <td class="button2">
                                    	<input type="button" class="site-btn update" value="강퇴" onclick="removeCheck()">
    								</td>
    								<%} %>
    							
    							</tr>	   
                                <%
		   							}
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
<script type="text/javascript">
function removeCheck() {

	 if (confirm("정말 강제 탈퇴시키겠습니까?") == true){    //확인
		 location.href="./AdminMemberDelete.ao?id=<%=mb.getId() %>";
	 }else{   //취소
	     return false;
	 }
}
</script>

    <!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>
</body>
</html>