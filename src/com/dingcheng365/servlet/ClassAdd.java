package com.dingcheng365.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dingcheng365.common.CommonFunction;
import com.dingcheng365.common.Message;

public class ClassAdd extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		String strID = "";
		String strName = "";
		String strSql = "";
		
		// 获取数据
		request.setCharacterEncoding("utf-8");
		strID = request.getParameter("tbID").trim();
		strName = request.getParameter("tbName").trim();
		
		// 校验
		if(strID=="")
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "编号不能为空!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = basePath + "ClassManage/ClassAdd.jsp";
	        msg.strLinkTarget = "_self";
	        request.setAttribute("msg",msg);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Share/ShowMessage.jsp");
	        requestDispatcher.forward(request,response);
	        return;
		}
		
		if(strName=="")
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "姓名不能为空!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = basePath + "ClassManage/ClassAdd.jsp";
	        msg.strLinkTarget = "_self";
	        request.setAttribute("msg",msg);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Share/ShowMessage.jsp");
	        requestDispatcher.forward(request,response);
	        return;
		}
		
		// 校验编号是否重复
		strSql = "select count(*) from Class where ID='" + strID + "'";
		int intCount = 0;
		intCount = CommonFunction.DBGetResultAsInt(strSql);
		if(intCount==-1)
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "从数据库获取数据失败!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = basePath + "ClassManage/ClassAdd.jsp";
	        msg.strLinkTarget = "_self";
	        request.setAttribute("msg",msg);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Share/ShowMessage.jsp");
	        requestDispatcher.forward(request,response);
	        return;
		}
		else if(intCount>0)
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "编号重复!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = basePath + "ClassManage/ClassAdd.jsp";
	        msg.strLinkTarget = "_self";
	        request.setAttribute("msg",msg);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Share/ShowMessage.jsp");
	        requestDispatcher.forward(request,response);
	        return;
		}
		
		// 添加
		strSql = "insert into Class values(";
		strSql += "'" + strID + "'";
		strSql += ",'" + strName + "'";
		strSql += ")";
		if(CommonFunction.DBExecuteNoReturn(strSql)==false)
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "添加失败!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = "ClassManage/ClassAdd.jsp";
	        msg.strLinkTarget = "_self";
	        request.setAttribute("msg",msg);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Share/ShowMessage.jsp");
	        requestDispatcher.forward(request,response);
	        return;
		}
		
		// 跳转
		response.sendRedirect(basePath + "ClassManage/ClassManage.jsp");
	}

}
