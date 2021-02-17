<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if((String)session.getAttribute("id") == null) {
%>
<!-- 로그인이 필요한 페이지의 경우 넣는 페이지 -->
<script>
	alert("로그인 후 이용해주세요.");
	location.href="./Login.me";
</script>
<%
}
%>