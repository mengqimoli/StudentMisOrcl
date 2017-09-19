<%@page import="com.dingcheng365.common.CommonFunction"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ClassEdit.jsp' starting page</title>
    
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
			if($("#hfID").val().trim()=="")
			{
				alert("编号不能为空!");
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
	
<%
	String strID = "";
	String strName = "";
	String strPage = "1";
	
	// 获取数据
	request.setCharacterEncoding("utf-8");
	strID = request.getParameter("id")!=null?request.getParameter("id").trim():"";
	strPage = request.getParameter("p")!=null?request.getParameter("p").trim():"1";
	
	// 从数据库获取数据
	ResultSet rs = null;
	String strSql = "";
	strSql = "select * from Class";
	strSql += " where ID='" + strID + "'";
	rs = CommonFunction.DBGetResultAsResultSet(strSql);
	if(rs!=null)
	{
		while(rs.next())
		{
			strName = rs.getString("Name").trim();
		}
	}

%>
  </head>
  
  <body>
  <center>
  <h4>班级信息编辑</h4>
  <form id="form1" name="form1" method="post" action="Servlet/ClassEdit.do">
    <table width="300" border="0" cellspacing="1" cellpadding="8" bgcolor="#000000">
      <tr bgcolor="#FFFFFF">
        <td>编号</td>
        <td>
        	<%=strID %> 
        	<input name="hfID" type="hidden" id="hfID" value="<%=strID %>" >
        	<input name="hfPage" type="hidden" id="hfPage" value="<%=strPage %>" >
        </td>
      </tr>
      <tr bgcolor="#FFFFFF">
        <td>名称</td>
        <td><input type="text" name="tbName" id="tbName" value="<%=strName %>"></td>
      </tr>
    </table>
    <p>
    <input type="button" name="btnEdit" id="btnEdit" value="编辑" onclick="javascript:CheckForm()">
        <input type="button" name="btnReturn" id="btnReturn" value="返回" onClick="javascript:history.back();">
    </p>
  </form>
  </center>
  </body>
</html>
