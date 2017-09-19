package com.dingcheng365.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dingcheng365.common.CommonFunction;
import com.dingcheng365.common.Page;

public class Class
{
	// 变量 *********************************************************************
	public String strID = "";
	public String strName = "";
	
	// 函数 *********************************************************************
	// 获取数据,不分页
	public static List<Class> GetData(String strSql)
	{
		List<Class> list = new ArrayList<Class>();
		
		ResultSet rs = null;
		rs = CommonFunction.DBGetResultAsResultSet(strSql);
			
		try
		{
			while(rs.next())
			{
				Class item = new Class();
				
				item.strID = rs.getString("ID").trim();
				item.strName = rs.getString("Name").trim();
						
				list.add(item);
			}
			
			return list;
		} 
		catch (SQLException e)
		{
			return list;
		}
	}
	
	// 获取数据,分页
	public static List<Class> GetData(Page p,String strSql,String strOrderBy)
	{
		List<Class> list = new ArrayList<Class>();
		
		int intFrom = 0;
		int intTo = 0;
		
		intFrom = (p.intCurrentPage-1) * p.intPageSize + 1;
		intTo = p.intCurrentPage * p.intPageSize;
		
		ResultSet rs = null;
		rs = CommonFunction.DBGetResultAsResultSet(strSql,intFrom,intTo);
			
		try
		{
			while(rs.next())
			{
				Class item = new Class();
				
				item.strID = rs.getString("ID").trim();
				item.strName = rs.getString("Name").trim();
						
				list.add(item);
			}
			
			return list;
		} 
		catch (SQLException e)
		{
			return list;
		}
	}
	
	
	
	
}
