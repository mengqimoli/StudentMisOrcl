package com.dingcheng365.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class InitServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig servletConfig) throws ServletException
	{
		ServletContext servletContext = servletConfig.getServletContext();
		
		// 获取日志文件夹实际路径
        Config.strLogPath = servletContext.getRealPath("WEB-INF/Log");
        
		// 读取配置文件并设置类Config
		String strConfigURI = servletConfig.getInitParameter("ConfigURI");
		
        String strConfigFile = servletContext.getRealPath(strConfigURI);		// 将文件的相对路径转化为绝对路径
        //String strConfigFile = "C:/java/tomcat7.0.27/webapps/StudentMisOrcl/WEB-INF/Config.xml";
		Config.Init(strConfigFile);	
	}

}
