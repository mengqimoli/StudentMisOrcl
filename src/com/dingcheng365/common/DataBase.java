package com.dingcheng365.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase
{
	private Connection con = null;
	
	// 构造
	public DataBase()
	{
		this.con = this.GetConnection();
	}
	
	// 获取连接字符串
    private String GetConnectionString()
    {
        String strConnectionString = "";    // 连接数据库字符串

        strConnectionString = "jdbc:oracle:thin:@" + Config.strDBServer + ":" + Config.strDBPort + ":" + Config.strDBName;
        return strConnectionString;
    }
    
    // 获取连接对象
    private Connection GetConnection()
    {
        String strConnectionString = this.GetConnectionString();   // 获取连接字符串

        try
		{
        	Class.forName(Config.strDBJDBCDriver);
        	con = DriverManager.getConnection(strConnectionString,Config.strDBUser,Config.strDBPassword);
			return con;
		} 
        catch (ClassNotFoundException e)
		{
        	if(Config.bLog==true)
			{
				Logger.Log("Common.DataBase.GetConnection(),加载驱动失败," + e.getMessage());
			}

			return null;
		} 
        catch (SQLException e)
		{
			if(Config.bLog==true)
			{
				Logger.Log("Common.DataBase.GetConnection(),创建连接对象失败," + e.getMessage());
			}

			return null;
		} 
    }
    
    // 判断数据库连接是否成功
    public Boolean IsConnected()
    {
    	try
		{
			if(this.con!=null && this.con.isClosed()==false)
			{
				return true;
			}
			else
			{
				return false;
			}
		} 
    	catch (SQLException e)
		{
    		if(Config.bLog==true)
			{
				Logger.Log("Common.DataBase.IsConnected(),判断连接对象是否连接成功失败," + e.getMessage());
			}
    		
    		return false;
		}
    }
    
    // 关闭数据库操作对象
    public void Close()
    {
    	if(this.IsConnected()==true)
    	{
    		try
			{
				this.con.close();
			} 
    		catch (SQLException e)
			{
    			if(Config.bLog==true)
    			{
    				Logger.Log("common.DataBase.Close(),关闭连接对象失败,"+e.getMessage());
    			}
			}
    	}
    }
    
    // 执行无返回值的SQL语句,例如:insert delete update
    public boolean ExecuteNoReturn(String strSql)
    {
        Statement stmt = null;

        // 校验数据库是否已连接
        if (this.IsConnected() == false)
        {
            this.con = this.GetConnection();
        }
        if (this.IsConnected() == false)
        {
            return false;
        }

        try
        {
            stmt = con.createStatement();
            stmt.execute(strSql);
            stmt.close();
            return true; 
        } 
        catch (SQLException e)
        {
            if(Config.bLog==true)
            {
            	Logger.Log("common.DataBase.ExecuteNoReturn(),执行" + strSql + "出错," + e.getMessage());
            }
            
            // 释放stmt
            if(stmt != null)
            {
                try
                {
                    stmt.close();
                } 
                catch (SQLException e1)
                {
                	if(Config.bLog==true)
                    {
                    	Logger.Log("common.DataBase.ExecuteNoReturn(),释放Statement出错," + e.getMessage());
                    }
                }
            }
            
            return false;
        }
    }
    
    // 执行无返回值的SQL语句数组,例如:insert delete update
    public boolean ExecuteNoReturn(String[] strSqlArray)
    {
        Statement stmt = null;
        String strSql = "";

        // 校验数据库是否已连接
        if (this.IsConnected() == false)
        {
            this.con = this.GetConnection();
        }
        if (this.IsConnected() == false)
        {
            return false;
        }
                
        try
		{
        	// 开始事务
        	this.con.setAutoCommit(false);
        	
        	// 循环执行SQL语句
			stmt = con.createStatement();
			for(int i=0;i<strSqlArray.length;i++)
			{
	    	   strSql = strSqlArray[i];
	    	   stmt.execute(strSql);
			}
			stmt.close();
			
			// 提交事务
		    this.con.commit();
			
		    return true;
		} 
        catch (SQLException e)
		{
        	try
        	{
        		if(Config.bLog==true)
        		{
        			strSql = "";
        			for(int i=0;i<strSqlArray.length;i++)
        			{
        				strSql += "(" + Integer.toString(i+1) + ")" + strSqlArray[i];
        			}
        			
        			Logger.Log("common.DataBase.ExecuteNoReturn(),执行语句" + strSql + "失败," + e.getMessage());
        		}
        		
        		this.con.rollback();
        		
        		if(stmt!=null)
        		{
        			stmt.close();
        		}
        		return false;
        		
        	}
        	catch (SQLException e1)
        	{
        		if(Config.bLog==true)
        		{
        			strSql = "";
        			for(int i=0;i<strSqlArray.length;i++)
        			{
        				strSql += "(" + Integer.toString(i+1) + ")" + strSqlArray[i];
        			}
        			
        			Logger.Log("common.DataBase.ExecuteNoReturn(),执行语句" + strSql + "滚回失败," + e.getMessage());
        		}
        		
        		return false;
        	}
		}
    }
    
    
    // 获取ResultSet数据
    public ResultSet GetResultAsResultSet(String strSql)
    {
        // 校验数据库是否已连接
        if (this.IsConnected() == false)
        {
            this.con = this.GetConnection();
        }
        if (this.IsConnected() == false)
        {
            return null;
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(strSql);
			return rs;
		} 
        catch (SQLException e)
		{
			if(Config.bLog==true)
			{
				Logger.Log("common.DataBase.GetResultAsResultSet(),执行语句" + strSql + "失败," + e.getMessage());
			}
        	
			try
			{
				stmt.close(); 
			} 
			catch (SQLException e1)
			{

			}
			        	
        	return null;
		}

    }
    
    // 获取ResultSet数据
    // strSql       sql语句,(1)在显示字段中必须有排序字段 (2)不允许有order by 子句
    // strOrderBy   排序字段
    // iFrom        开始行
    // iTo          结束行
    public ResultSet GetResultAsResultSetFromTo(String strSql, int intFrom, int intTo)
    {
        // 校验数据库是否已连接
        if (this.IsConnected() == false)
        {
            this.con = this.GetConnection();
        }
        if (this.IsConnected() == false)
        {
            return null;
        }
        
        String strSqlTmp = "";
        Statement stmt = null;
        ResultSet rs = null;
                
        try
		{
        	//strSqlTmp = "select myt1.* from (select row_number() over (order by " + strOrderBy + ") as rownumber,myt2.* from (" + strSql + ") as myt2) as myt1 where rownumber between " + Integer.toString(intFrom) + " and " + Integer.toString(intTo);
        	
        	strSqlTmp = "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (" + strSql + ") A ) WHERE RN BETWEEN "+ Integer.toString(intFrom) + " AND " + Integer.toString(intTo);
        	
			stmt = con.createStatement();
			rs = stmt.executeQuery(strSqlTmp);
			return rs;
		} 
        catch (SQLException e)
		{
			if(Config.bLog==true)
			{
				Logger.Log("common.DataBase.GetResultAsResultSet(),执行语句" + strSqlTmp + "失败," + e.getMessage());
			}
        				
			try
			{
				stmt.close(); 
			} 
			catch (SQLException e1)
			{

			}
			        	
        	return null;
		}
    }
    
    // 获取数量数据,例如 select count(*) from tablename
    // 异常返回-1
    public int GetResultAsInt(String strSql)
    {
        // 校验数据库是否已连接
        if (this.IsConnected() == false)
        {
            this.con = this.GetConnection();
        }
        if (this.IsConnected() == false)
        {
            return -1;
        }
        
        int intCount = 0;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(strSql);
			
	        while(rs.next())
	        {
	        	intCount = rs.getInt(1);
	        }
	        
	        rs.close();
	        stmt.close();
	        
	        return intCount;
		} 
        catch (SQLException e)
		{
        	if(Config.bLog==true)
			{
				Logger.Log("common.DataBase.GetResultAsInt(),执行语句" + strSql + "失败," + e.getMessage());
			}
        	
        	try
			{
        		stmt.close();
			} 
        	catch (SQLException e1)
			{
				
			}
        	
        	return -1;
		}
    }
    
    
    

}
