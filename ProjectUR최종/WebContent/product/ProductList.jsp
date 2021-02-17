<%@page import="net.product.db.ProductDAO"%>
<%@page import="net.product.db.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
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
    <link rel="stylesheet" href="./css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="./css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="./css/themify-icons.css" type="text/css">
    <link rel="stylesheet" href="./css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="./css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="./css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="./css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="./css/style.css" type="text/css">
    <link rel="stylesheet" href="./css/slicknav.min.css" type="text/css">
    
</head>
<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	
	<!-- 헤더 -->
    <%@include file="../inc/header.jsp" %>

    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="./index.jsp"><i class="fa fa-home"></i> Home</a>
                        <% 
                        request.setCharacterEncoding("UTF-8");
                    	
                    	//검색어 정보를 저장
                    	
                        
                        int category =Integer.parseInt(request.getParameter("category"));
                        
                        if(category == 0){%>
                    		<span>전체</span>
                    	<%}if(category == 1){%>
                    		<span>피로/간</span>
	                	<%}if(category == 2){%>
	                    	<span>수면/스트레스</span>
	                	<%}if(category == 3){%>
	                    	<span>피부</span>
	                	<%}if(category == 4){%>
	                    	<span>눈</span>
	                    <%}if(category == 5){%>
	                    	<span>두뇌활동</span>
	                    <%}if(category == 6){%>
	                    	<span>심장/혈관/혈당</span>
	                	<%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->

    <!-- Product Shop Section Begin -->
    <section class="product-shop spad">
        <div class="container">
            <div class="row">
                
                
                <%
				ArrayList productList = (ArrayList)request.getAttribute("productList");
				
				
				
				System.out.println("@"+productList);
				ProductBean pb = new ProductBean();
				String pbimg = "";
				
				String pageNum = (String)request.getAttribute("pageNum");	
				
				int count = (Integer)request.getAttribute("count");	
				int pageCount = (Integer)request.getAttribute("pageCount");	
				int pageBlock = (Integer)request.getAttribute("pageBlock");	
				int startBlock = (Integer)request.getAttribute("startBlock");	
				int endBlock =(Integer)request.getAttribute("endBlock");	
				int startPage = (Integer)request.getAttribute("startPage");	
				int pageSize = (Integer)request.getAttribute("pageSize");	
				int endPage = startPage + pageSize -1;
				if(endPage > count){
					endPage = count;
				}
				
				
				System.out.println("@@@@@@@@@@@@category = " + category);
				
				%>
            
            
                
                
                <div class="col-lg-12 order-1 order-lg-2">
                    <div class="product-show-option">
                        <div class="row">
                            <div class="col-lg-7 col-md-7">
                                <div class="select-option">
                                    	<select class="sorting" id="orderBy" onchange="getSelectValue();">
                                    	<%
                                    	/* String odb = request.getParameter("odb"); */
	
                                    	String odb = (String)request.getAttribute("odb");
                                    	
                                    	if(odb == null){%>
                                    	<option value="num_desc" selected="selected">최근등록순</option>
                                		<%}
                                    	if(odb.equals("num_desc")){%>
                                        	<option value="num_desc" selected="selected">최근등록순</option>
                                    	<%}else{
                                    		%><option value="num_desc">최근등록순</option><%
                                    	}%><%
                                    	if(odb.equals("star_avg")){%>
                                        	<option value="star_avg" selected="selected">별점순</option>
                                    	<%}else{
                                    		%><option value="star_avg">별점순</option><%
                                    	}%><%
                                    	if(odb.equals("price_high")){%>
                                        	<option value="price_high" selected="selected">가격높은순</option>
                                    	<%}else{
                                    		%><option value="price_high">가격높은순</option><%
                                    	}%><%
                                    	if(odb.equals("price_low")){%>
                                        	<option value="price_low" selected="selected">가격낮은순</option>
                                    	<%}else{
                                    		%><option value="price_low">가격낮은순</option><%
                                    	}%><%
                                    	if(odb.equals("readcount")){%>
                                        	<option value="readcount" selected="selected">조회순</option>
                                    	<%}else{
                                    		%><option value="readcount">조회순</option><%
                                    	}%>
                                        
                                    	</select>
                                    
                                    <!-- search  -->	
                                   <%
                               	
	                               	
	                               	
	                               	
                                   %>
                                    
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-5 text-right">
                            <!-- 카테고리별로 적용시키기 -->
                                <p>Show &nbsp;<%= startPage %> ~ <%= endPage %> &nbsp; Of&nbsp; <%= count %> Product</p>
                            </div>
                        </div>
                    </div>
                    
                    <input type= "hidden" value="${param.search }" id="search">
                <script type="text/javascript">
                
	                function getSelectValue(){
	                	var selectedValue = document.getElementById("orderBy").value;
	                	var search = document.getElementById("search").value;
	                	
	                	console.log("V: selectedValue : "+selectedValue);
	            		<%
	            		request.setCharacterEncoding("UTF-8");
	            		
	            		if(search == null || search.equals("")){
	            			%>
	            			location.href="./ProductList.p?category="+${param.category}+"&odb="+selectedValue;
           				<%}else{
	            			%>
	            			location.href="./ProductList.p?category="+${param.category}+"&odb="+selectedValue+"&search="+search;
            			<%}
	            		%>
	                	
	                	
	                }
                
                </script>
                    
                    
                    <div class="product-list">
                        <div class="row">
                        	<c:forEach items="${requestScope.productList}" var="item">
                        <!-- 제품리스트 목록  -->
                      <c:set var="pi" value="${pageScope.item }" />
            
				             <!-- 별점 변수 생성 -->
				            <c:set var="p_num" value="${pi.p_num }"></c:set>
				            <% int p_num = (int)pageContext.getAttribute("p_num");
				             	ProductDAO pdao = new ProductDAO();
								double star_avg = pdao.getStarAvg(p_num); 
								pageContext.setAttribute("star_avg", star_avg); %>
							<c:set var="star_avg" value="${pageScope.star_avg }"/>
							
							  <!-- 조회수 변수 설정  -->
				          	 <% int review_count =  pdao.getReviewCount(p_num); 
				              	pageContext.setAttribute("review_count", review_count); %>
				             <c:set var="review_count" value="${pageScope.review_count }"/>
				                        
                        	<div class="col-lg-4 col-sm-6">
                                <div class="product-item">
                                    <div class="pi-pic">
                                        <img class="main_img" src="./upload/${pi.img_main}" alt="" width="360" height="360">
                                        <!--270 * 330  -->
                                        <div class="sale pp-sale">Sale</div>
                                        <ul>
                                          <li class="quick-view"><a href="./Product.p?p_num=${p_num }">+ 상세보기</a></li>
                                        </ul>
                                    </div>
                                    <div class="pi-text">
                                    <!-- 카테고리 분류  -->
                                        <div class="catagory-name">
                                        <c:if test="${pi.category == 1 }">피로/간</c:if>
                                        <c:if test="${pi.category == 2 }">수면/스트레스</c:if>
                                        <c:if test="${pi.category == 3 }">피부</c:if>
                                        <c:if test="${pi.category == 4 }">눈</c:if>
                                        <c:if test="${pi.category == 5 }">두뇌활동</c:if>
                                        <c:if test="${pi.category == 6 }">심장/혈관/혈당</c:if>
                                        </div>
                                        <div class="product-details">
                                         <div class="pd-rating">
                                         <!-- 평균 별점 -->
                                         <%@include file="./star_avg.jsp" %>
                                        <span>(${review_count})</span><!-- 조회수  -->
                                        </div>
                                        </div>
                                        
                                        <a href="./Product.p?p_num=${p_num }">
                                            <h5>${pi.p_name} 
                                             <%-- [조회수 : <%=readcount %> ] --%></h5>
                                        </a>
                                        <div class="product-price" >
                                        
                                        
                                        	<fmt:formatNumber value="${pi.p_saleprice}" pattern="#,###" />원
                                        	
                                            <span><fmt:formatNumber value="${pi.p_price}" pattern="#,###" />원</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                       	 </c:forEach>
                        </div>
                    </div>
                    
                    
                    <div class="loading-more">
                    
    <%
	//페이징 처리 (하단부)
	if(count != 0){
		//이전
		if(startBlock > pageBlock){
			%>
			<a href="./ProductList.p?category=<%=category %>&pageNum=<%=1 %>&odb=<%=odb%>&search=${param.search}"> << </a>
			&nbsp;&nbsp;
			<a href="./ProductList.p?category=<%=category %>&pageNum=<%=startBlock - 1 %>&odb=<%=odb%>&search=${param.search}"> < </a>
			&nbsp;
			<%
		}
		
		//페이지숫자
		for(int i=startBlock;i<= endBlock; i++){
			if(pageNum.equals(i+"")){%>
			<a href="./ProductList.p?category=<%=category %>&pageNum=<%=i%>&odb=<%=odb%>&search=${param.search}" >[<u><%=i %></u>]</a> <!--수정 : 선택된 블럭 색변경 요청  -->
			  <%}else{%>
			<a href="./ProductList.p?category=<%=category %>&pageNum=<%=i%>&odb=<%=odb%>&search=${param.search}">[<%=i %>]</a>
			<%}			
		}
		//다음
		if(endBlock < pageCount){
		%>&nbsp;
		<a href="./ProductList.p?category=<%=category %>&pageNum=<%= startBlock + pageBlock%>&odb=<%=odb%>&search=${param.search}"> > </a>
		&nbsp;&nbsp;
		<a href="./ProductList.p?category=<%=category %>&pageNum=<%= pageCount%>&odb=<%=odb%>&search=${param.search}"> >> </a>
		<%
		}
		
	}
	
	%>
                     </div>
                    </div>
                </div>
            </div>
    </section>
    <!-- Product Shop Section End -->
 
    <!-- 푸터 -->
    <%@include file="../inc/footer.jsp" %>

    <!-- Js Plugins -->
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.countdown.min.js"></script>
    <script src="./js/jquery.nice-select.min.js"></script>
    <script src="./js/jquery.zoom.min.js"></script>
    <script src="./js/jquery.dd.min.js"></script>
    <script src="./js/jquery.slicknav.js"></script>
    <script src="./js/owl.carousel.min.js"></script>
    <script src="./js/main.js"></script>
</body>
</html>