<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>웰컴 페이지(<%= session.getId() %>)</h2>
<%
	String authID = (String) session.getAttribute("authID");
	if(StringUtils.isNotBlank(authID)){
		%>
		<form name = "logoutForm" action="<%= request.getContextPath() %>/login/logout.do" method="post"></form>
		현재로그인 유저 : <%= authID %> <a href="#" onclick="document.logoutForm.submit();">로그아웃</a>
		<%
	}else{
		%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인하기</a>
		<%
	}
%>
</body>
</html>