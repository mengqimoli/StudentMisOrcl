<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ClassAdd.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="JS/jquery.js" ></script>
	<script type="text/javascript" src="JS/Validate.js"></script>
	<script type="text/javascript">
		function CheckForm()
		{
			if($("#tbID").val().trim()=="")
			{
				alert("编号不能为空!");
				$("#tbID").focus();
				return false;
			}
			
			if($("#tbName").val().trim()=="")
			{
				alert("名称不能为空!");
				$("#tbName").focus();
				return false;
			}
			
			$("#form1").submit();
		}
	
		
	
	
	</script>
  </head>
  
  <body>
  <center>
  <h4>班级信息添加</h4>
  <form id="form1" name="form1" method="post" action="Servlet/ClassAdd.do">
    <table width="300" border="0" cellspacing="1" cellpadding="8" bgcolor="#000000">
      <tr bgcolor="#FFFFFF">
        <td>编号</td>
        <td><input type="text" name="tbID" id="tbID"></td>
      </tr>
      <tr bgcolor="#FFFFFF">
        <td>名称</td>
        <td><input type="text" name="tbName" id="tbName"></td>
      </tr>
    </table>
    <p>
    	<input type="button" name="btnAdd" id="btnAdd" value="添加" onclick="javascript:CheckForm()">
        <input type="button" name="btnReturn" id="btnReturn" value="返回" onClick="javascript:history.back();">
    </p>
  </form>
  </center>
  </body>
</html>
