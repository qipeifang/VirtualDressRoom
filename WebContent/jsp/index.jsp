<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + 
                                      request.getServerName() + ":" +
                                      request.getServerPort() + path;
%>    
<html>
<head>
<title>梦想试衣间</title>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
</head>
<body>
id:<%=request.getSession().getAttribute("userId")%>
info:<%=request.getSession().getAttribute("user")%>


</body>
</html>