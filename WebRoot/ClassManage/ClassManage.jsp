<%@page import="com.dingcheng365.common.Page"%>
<%@page import="com.dingcheng365.bean.Class"%>
<%@page import="com.dingcheng365.common.CommonFunction"%>
<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="UTF-8"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ClassManage.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
	
	<script type="text/javascript" src="JS/jquery.js"></script>
	<script type="text/javascript" src="JS/jqPaginator.js"></script>
	
	
<%
	request.setCharacterEncoding("UTF-8"); 
	String strSql = "";
	int intCurrentPage = 0;
	
	// 获取数据
	request.setCharacterEncoding("utf-8");
	intCurrentPage = request.getParameter("p")!=null? Integer.parseInt(request.getParameter("p").trim()):1; 
	
	// 创建分页对象
	strSql = "select count(*) from Class";
	Page p = new Page(intCurrentPage,strSql);
	
	// 获取数据库数据
	strSql = "select * from Class order by ID";
	List<Class> list = Class.GetData(p, strSql,"ID");
 %>
  </head>
  
  <body>
  <center>
  <h3>班级信息管理</h3>
  
  <p>
  <a href="ClassManage/ClassAdd.jsp" target="_self">添加</a>
  <a href="Index.jsp" target="_self">返回</a>
  </p>
  
  <table width="300" border="1" cellspacing="1" cellpadding="8" bgcolor="#000000">
      <tr bgcolor="#FFFFFF">
        <th>编号</th>
        <th>名称</th>
        <th>操作</th>
      </tr>
      <%
      	for(int i=0;i<list.size();i++)
      	{
      		Class item = list.get(i);
      %>
      	<tr bgcolor="#FFFFFF">
        	<td><%=item.strID %></td>
        	<td><%=item.strName %></td>
        	<td>
        		<a href="ClassManage/ClassEdit.jsp?id=<%=item.strID %>&p=<%=p.intCurrentPage %>" target="_self" >编辑</a> 
        		<a href="Servlet/ClassDelete.do?id=<%=item.strID %>&p=<%=p.intCurrentPage %>" target="_self" onclick="javascript:return confirm('确认要删除?');">删除</a>
        	</td>
      	</tr>
      	
      <%}%>

    </table>
  	
  	<!-- 分页栏 -->
    <ul class="pagination" id="pagination1"></ul>
    <script type="text/javascript">
	    $("#pagination1").jqPaginator({
            totalPages: <%=p.intTotalPage %>,
            visiblePages: 5,
            currentPage: <%=p.intCurrentPage %>,
            first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页<\/a><\/li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页<\/a><\/li>',
            last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function (n,type) {
                if(type=="init")
                {
                }
                else if(type=="change")
                {
                	window.location.href="<%=basePath + "ClassManage/ClassManage.jsp?p=" %>" + n;
                }
            }
        });
	
	</script>
  </center>
  

  </body>
</html>
