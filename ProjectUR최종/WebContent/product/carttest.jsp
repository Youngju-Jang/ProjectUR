<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uR</title>
</head>
<body>

	<%
	request.setCharacterEncoding("UTF-8");
	int c_p_count = Integer.parseInt(request.getParameter("c_p_count"));
	%>
	<%= c_p_count %>

</body>
</html>