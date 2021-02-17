<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%  String search = ""; 
    if( !search.equals("") || search != null){
    	search = request.getParameter("search");
    	}	
    if(search == null){
    	search = "";
    }
    String sorting = request.getParameter("sorting");
    %>
<div class="advanced-search">
	<form action="./ProductList.p" method="get" name="searF"> 
		<input type="hidden" name="category" value="0">
		<div class="input-group">
		<%
		if(search.equals("") || search == null){
			%><input type="text" placeholder="What do you need?" name="search" id="p_search">
		<%
		}else{
			%><input type="text" placeholder="What do you need?" value="${param.search }" name="search" id="p_search">
		<%
		}
		%>
			<button type="submit"><i class="ti-search"></i></button>
		</div>
	</form>
</div>