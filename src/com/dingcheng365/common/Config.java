package com.dingcheng365.common;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Config
{
	// 数据库服务器配置信息
    public static String strDBJDBCDriver = "";		// 数据库驱动,Oracle数据库 oracle.jdbc.driver.OracleDriver,SQLServer2008R2 数据库 com.microsoft.sqlserver.jdbc.SQLServerDriver
	public static String strDBServer = "";    		// 数据库服务器名或数据库服务IP地址
    public static String strDBPort = "";			// 数据库服务器端口号
    public static String strDBName = "";          // 数据库名
    public static String strDBUser = "";          // 访问数据库用户名
    public static String strDBPassword = "";      // 访问数据密码
    

    // 日志配置信息
    public static boolean bLog = true;           // 是否记录日志
    public static String strLogPath = "";         // 日志文件路径,注意最后没有"/"

    // 分页显示信息
    public static int intPageSize = 15;           // 分页显示时,每页显示的记录数

    // 日期格式
    public static String strDateFormat = "yyyy-MM-dd";  				// 日期格式,例如:2001-01-01
    public static String strDateTimeFormat = "yyyy-MM-dd HH:mm:ss";  	// 日期格式,例如:2001-01-01 20:01:01
    
    // 根据xml文件赋值配置选项信息
	public static boolean Init(String strFile)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder(false);
		    Document doc = builder.build("file:///" + strFile);
		    Element root = doc.getRootElement();
		    
		    // 数据库服务器配置信息
		    Config.strDBJDBCDriver = root.getChild("DBJDBCDriver").getTextTrim();
		    Config.strDBServer = root.getChild("DBServer").getTextTrim();
		    Config.strDBPort = root.getChild("DBPort").getTextTrim();
		    Config.strDBName = root.getChild("DBName").getTextTrim();          
		    Config.strDBUser = root.getChild("DBUser").getTextTrim();          
		    Config.strDBPassword = root.getChild("DBPassword").getTextTrim();   
		    
		    // 日志配置信息
		    Config.bLog = Boolean.parseBoolean(root.getChild("IsLog").getTextTrim());
		    
		    // 分页显示信息
		    Config.intPageSize = Integer.parseInt(root.getChild("PageSize").getTextTrim());
			
		}
		catch (JDOMException e)
        {
            Logger.Log("Config.Init(),读取配置文件失败," + e.getMessage());
        } catch (IOException e)
		{
        	Logger.Log("Config.Init(),读取配置文件失败," + e.getMessage());
		}
				
		return true;
	}
	
}
