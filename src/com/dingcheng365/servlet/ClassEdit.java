package com.dingcheng365.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dingcheng365.common.Message;
import com.dingcheng365.common.CommonFunction;

public class ClassEdit extends HttpServlet
{	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		String strID = "";
		String strName = "";
		String strPage = "";
		
		// 获取数据
		request.setCharacterEncoding("utf-8");
		strID = request.getParameter("hfID").trim();
		strName = request.getParameter("tbName").trim();
		strPage = request.getParameter("hfPage").trim();
		
		// 编辑
		String strSql = "";
		strSql = "update Class set";
		strSql += " Name='" + strName + "'";
		strSql += " where id='" + strID + "'";
		if(CommonFunction.DBExecuteNoReturn(strSql)==false)
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "编辑失败!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = "ClassManage/ClassEdit.jsp?id=" + strID + "&p=" + strPage;
	        msg.strLinkTarget = "_self";
	        request.setAttribute("msg",msg);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Share/ShowMessage.jsp");
	        requestDispatcher.forward(request,response);
	        return;
		}
		
		// 跳转
		response.sendRedirect(basePath + "ClassManage/ClassManage.jsp?p=" + strPage);
	}

}
