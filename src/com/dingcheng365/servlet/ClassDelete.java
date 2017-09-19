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

public class ClassDelete extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		String strID = "";
		String strSql = "";
		String strPage = "";
		
		// 获取数据
		request.setCharacterEncoding("utf-8");
		strID = request.getParameter("id")!=null?request.getParameter("id").trim():"";
		strPage = request.getParameter("p")!=null?request.getParameter("p").trim():"1";
		
		// 删除
		strSql = "delete from class";
		strSql += " where id='" + strID + "'";
		if(CommonFunction.DBExecuteNoReturn(strSql)==false)
		{
			// 跳转到信息页
	        Message msg = new Message();
	        msg.strCotent = "删除失败!";
	        msg.strLinkName = "[返回]";
	        msg.strLinkURL = basePath + "ClassManage/ClassManage.jsp?p=" + strPage;
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
