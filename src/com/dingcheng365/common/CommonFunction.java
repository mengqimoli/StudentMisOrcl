package com.dingcheng365.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CommonFunction
{
	// 数据库通用操作函数***************************************************************
    // 获取数量数据,例如 select count(*) from tablename 
    // 获取异常返回-1
	public static int DBGetResultAsInt(String strSql)
    {
        int iCount = -1;
        DataBase db = new DataBase();   // 创建数据库操作对象

        // 校验数据库连接是否成功
        if (db.IsConnected() == false)
        {
            db.Close(); // 释放数据库操作对象资源
            return iCount;
        }

        iCount = db.GetResultAsInt(strSql);

        db.Close(); // 释放数据库操作对象资源

        return iCount;
    }
	
	// 获取ResultSet数据
    // 获取数据异常返回null
	public static ResultSet DBGetResultAsResultSet(String strSql)
    {
        DataBase db = new DataBase();   // 创建数据库操作对象

        // 校验数据库连接是否成功
        if (db.IsConnected() == false)
        {
            db.Close(); // 释放数据库操作对象资源
            return null;
        }

        ResultSet rs = null;
        rs = db.GetResultAsResultSet(strSql);
        
        return rs;
    }
	
    // 获取DataSet数据
    // strSql       sql语句,(1)在显示字段中必须有排序字段 (2)不允许有order by 子句
    // strOrderBy   排序字段
    // iFrom        开始行
    // iTo          结束行
	public static ResultSet DBGetResultAsResultSet(String strSql, int intFrom, int intTo)
    {
        DataBase db = new DataBase();   // 创建数据库操作对象

        // 校验数据库连接是否成功
        if (db.IsConnected() == false)
        {
            db.Close(); // 释放数据库操作对象资源
            return null;
        }

        ResultSet rs = null;
        rs = db.GetResultAsResultSetFromTo(strSql, intFrom, intTo);
        
        return rs;
    }
    
    // 执行无返回值的SQL语句,例如:insert delete update
	public static boolean DBExecuteNoReturn(String strSql)
    {
        DataBase db = new DataBase();   // 创建数据库操作对象

        // 校验数据库连接是否成功
        if (db.IsConnected() == false)
        {
            db.Close(); // 释放数据库操作对象资源
            return false;
        }

        boolean bResult = false;
        bResult = db.ExecuteNoReturn(strSql);
        db.Close(); // 释放数据库操作对象资源

        return bResult;
    }
    
    // 执行无返回值的SQL语句数组,例如:insert delete update
	public static boolean DBExecuteNoReturn(String[] strSqlArray)
    {
        DataBase db = new DataBase();   // 创建数据库操作对象

        // 校验数据库连接是否成功
        if (db.IsConnected() == false)
        {
            db.Close(); // 释放数据库操作对象资源
            return false;
        }

        boolean bResult = false;
        bResult = db.ExecuteNoReturn(strSqlArray);
        db.Close(); // 释放数据库操作对象资源

        return bResult;
    }

    // 根据关键字获取值,关键字和值都是字符串型
	public static String DBGetValueFromKey(String strKeyName, String strValueName, String strTableName, String strKey)
    {
        String strSql = "";
        ResultSet rs = null;
        String strValue = "";

        strSql = "select " + strValueName + " from " + strTableName + " where " + strKeyName + "='" + strKey + "'";
        rs = CommonFunction.DBGetResultAsResultSet(strSql);
        
        if(rs==null)
        {
        	return "";
        }
        
        try
        {
        	while(rs.next())      
            {      
            	strValue = rs.getString(strValueName);
            }  
        	
        	return strValue;
        }
        catch(SQLException e)
        {
        	return "";
        }
    }
    
	// 根据关键字获取值,关键字和值都是字符串型
	static public String DBGetValueFromKey(String strKeyName, String strValueName, String strTableName, String strKey, String strWhere)
    {
        String strSql = "";
        ResultSet rs = null;
        String strValue = "";

        strSql = "select " + strValueName + " from " + strTableName + " where " + strKeyName + "='" + strKey + "' and " + strWhere;
        rs = CommonFunction.DBGetResultAsResultSet(strSql);
        if (rs == null)
        {
            return "";
        }

        try
        {
        	while(rs.next())      
            {      
            	strValue = rs.getString(strValueName);
            }  
        	
        	return strValue;
        }
        catch(SQLException e)
        {
        	return "";
        }
    }
    
	// 添加单引号,例如'1'->''1''(解决单引号不能插入到数据库中问题)
	public static String AddSingleQuote(String str)
    {
        return str.replace("'", "''");
    }
	
	// 日期时间函数 *****************************************************************
    // 获取格式化后的时间字符串
	public static String GetFormatDateTime(Date datX,String strFormat)
	{
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(strFormat);			
		String time = f.format(datX);
		return time;
	}
	
	
}
