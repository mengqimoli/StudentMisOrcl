<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dingcheng365.common.Message" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowMessage.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%
	request.setCharacterEncoding("UTF-8"); 
	Message msg = null;
	msg = (Message)request.getAttribute("msg");
%>
  </head>
  
  <body>
  <center>
  <h4>学生管理系统</h4>
  
  <p>
  	信息: <%=msg.strCotent %>  
  </p>
  
  <p>
  	<a href="<%=msg.strLinkURL %>" target="<%=msg.strLinkTarget %>"><%=msg.strLinkName %></a>
  </p>
  </center>
  </body>
</html>
